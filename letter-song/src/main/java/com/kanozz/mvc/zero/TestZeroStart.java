package com.kanozz.mvc.zero;

import com.kanozz.mvc.TomcatUtil;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.SourceFilteringListener;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.core.MethodParameter;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class TestZeroStart {



	/**
	 *
	 * 对于web.xml
	 *
	 * <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	 *
	 * 会添加一个监听器 去加载 spring 环境
	 *
	 * <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	 *
	 * 会添加一个Servlet去 加载 spring mvc 环境
	 *
	 * 根据 servlet 3.0规范 我们只需要实现 WebApplicationInitializer
	 * 就可以在 容器启动完成这两个对象的实例化
	 * @see org.springframework.web.SpringServletContainerInitializer
	 *
	 * 而spring mvc默认有个抽象实现
	 * @see AbstractDispatcherServletInitializer#onStartup(javax.servlet.ServletContext)
	 * 在onStartUp()里面创建了这两个对象
	 *
	 * 自定义实现类实现默认的抽象类并且配置到SPI里面那么会回调抽象类中的 onStartUp()
	 *
	 * 1. spring容器
	 *
	 * 1.1
	 * 在 AbstractContextLoaderInitializer 里面完成了spring环境的创建并添加到 ContextLoaderListener
	 * 此时只是创建没有进行容器的刷新操作
	 * @see AbstractContextLoaderInitializer#onStartup(javax.servlet.ServletContext)
	 *
	 * 具体就是 调用其抽象子类的实现方法创建一个spring容器
	 * @see AbstractAnnotationConfigDispatcherServletInitializer#createRootApplicationContext()
	 * @see org.springframework.web.context.support.AnnotationConfigWebApplicationContext
	 *
	 * 此时会调用具体子类实现的获取配置类的方法将配置类注册到上面创建的spring容器中
	 * @see AbstractAnnotationConfigDispatcherServletInitializer#getRootConfigClasses()
	 *
	 *
	 * 1.2
	 * 当web容器启动后会调用Listener的 contextInitialized()方法，此时完成了spring容器的初始化
	 * 并且将spring容器设置到 web容器上下文中
	 * @see org.springframework.web.context.ContextLoaderListener#contextInitialized(ServletContextEvent)
	 *
	 * 至此spring容器完成
	 *
	 *
	 * 2. mvc容器
	 *
	 * 2.1 和1.2执行没有什么时序关系
	 * 在1.1创建完成后会立即调用 AbstractDispatcherServletInitalizer 的 onStartup
	 * @see AbstractDispatcherServletInitializer#onStartup(ServletContext)
	 *
	 * 最终调用 registerDispatcherServlet()创建了mvc容器
	 * @see AbstractDispatcherServletInitializer#registerDispatcherServlet(ServletContext)
	 *
	 * 调用的仍是抽象实现类的createServletApplicationContext()
	 * @see AbstractAnnotationConfigDispatcherServletInitializer#createServletApplicationContext()
	 * @see org.springframework.web.context.support.AnnotationConfigWebApplicationContext
	 * 并且调用具体实现类将mvc的配置类注册到容器中
	 *
	 * 创建了DispatcherServlet 并且将mvc 容器传入，设置web容器启动回调init()
	 *
	 * 2.2
	 * web容器回调servlet的init()
	 * @see org.springframework.web.servlet.HttpServletBean#init()
	 * 调用 FrameworkServlet的 initServletBean() 完成 spring容器和mvc容器的关联
	 * 并且进行 mvc容器的初始化
	 * @see FrameworkServlet#initServletBean()
	 * @see FrameworkServlet#initWebApplicationContext()
	 *
	 * 并且调用 configureAndRefreshWebApplicationContext() 注册了一个监听器 用于监听
	 * mvc容器的初始化完成事件,这里不会监听到spring的事件因为
	 * web容器保证了Listener调用先于servlet
	 * @see FrameworkServlet#configureAndRefreshWebApplicationContext(ConfigurableWebApplicationContext)
	 *
	 * 监听 ContextRefreshedEvent
	 * @see FrameworkServlet.ContextRefreshListener#onApplicationEvent(ContextRefreshedEvent)
	 * 只有发布事件的数据源和监听的数据源相同的时候才会调用对应的处理方法
	 * @see SourceFilteringListener#onApplicationEvent(ApplicationEvent)
	 *
	 * 2.3
	 * mvc容器刷新完成后通过事件发布调用到 FrameworkServlet的onApplicationEvent()
	 * @see FrameworkServlet#onApplicationEvent(ContextRefreshedEvent)
	 *
	 * 最终调用到 DispatcherServlet的onRefresh() 并从容器中完成 HanlderMapping HandlerAdpater HandlerExceptionResolver
	 * bean的收集
	 * @see org.springframework.web.servlet.DispatcherServlet#onRefresh(ApplicationContext)
	 * @see org.springframework.web.servlet.DispatcherServlet#initStrategies(ApplicationContext)
	 *
	 *
	 * 这些bean的创建时通过注解 @EnableWebMvc 导入配置类 DelegatingWebMvcConfiguration 来添加的
	 * @see org.springframework.web.servlet.config.annotation.EnableWebMvc
	 * @see org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration
	 * 最终是在 WebMvcConfigurationSupport 添加一些bean
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
	 *
	 * e.g HandlerMapping
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#requestMappingHandlerMapping(ContentNegotiationManager, FormattingConversionService, ResourceUrlProvider)
	 *
	 *
	 * 3. 请求过程
	 *
	 * 3.1
	 * 首先调用 FrameworkServlet的 servic() ,如果不是patch请求那么会调用父类的处理
	 * @see FrameworkServlet#service(HttpServletRequest, HttpServletResponse)
	 * @see org.springframework.web.servlet.HttpServletBean#service(HttpServletRequest, HttpServletResponse)
	 *
	 * 然后调用doGet() ,doPost()
	 * @see FrameworkServlet#doPost(HttpServletRequest, HttpServletResponse) 
	 * 调用 processRequest()将request 和 response 放入ThreadLocal
	 * @see FrameworkServlet#processRequest(HttpServletRequest, HttpServletResponse)
	 *
	 *
	 * 最终调用 initContextHolders() 存放到 ThreadLocal
	 * @see FrameworkServlet#initContextHolders(HttpServletRequest, LocaleContext, RequestAttributes)
	 * @see org.springframework.web.context.request.RequestContextHolder#setRequestAttributes(RequestAttributes, boolean)
	 *
	 * 3.2 
	 * 调用 DispatcherServlet的 doDispatch() 处理请求
	 * @see org.springframework.web.servlet.DispatcherServlet#doService(HttpServletRequest, HttpServletResponse)
	 * @see org.springframework.web.servlet.DispatcherServlet#doDispatch(HttpServletRequest, HttpServletResponse)
	 *
	 * 3.2.1 处理文件上传
	 * @see org.springframework.web.servlet.DispatcherServlet#checkMultipart(HttpServletRequest)
	 *
	 * 3.2.2 获取请求url 和 处理方法的映射关系
	 * 此时返回的 HandlerMapping 是包含拦截器的
	 * @see org.springframework.web.servlet.DispatcherServlet#getHandler(HttpServletRequest)
	 * 
	 * 具体就是判断每个HandlerMapping是否支持处理 这个请求 RequestMappingHandlerMapping
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMapping#getHandler(HttpServletRequest)
	 *
	 * 调用具体实现子类实现的 getHandlerInternal() 获取处理当前url的方法，其中使用读写锁来获取并发控制
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping#getHandlerInternal(HttpServletRequest)
	 *
	 * 调用 lookupHandlerMethod() 获取处理方法
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping#lookupHandlerMethod(String, HttpServletRequest)
	 * 先去缓存中获取 url 的映射
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.MappingRegistry#getMappingsByDirectPath(String)
	 * url和参数的缓存建立是在 HandlerMapping 的回调方法中做的
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping#afterPropertiesSet()
	 * 具体加载匹配的逻辑
	 * @see AbstractHandlerMethodMapping#initHandlerMethods()
	 * 最终完成映射关联的方法 AbstractHandlerMethodMapping 的 detectHandlerMethods() detect:发现
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping#detectHandlerMethods(Object)
	 * 注册到
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.MappingRegistry#register(Object, Object, Method) 
	 *
	 *
	 *
	 *
	 */
	@Test
	public void testZero() throws Exception{
		Tomcat tomcat = new Tomcat();
		Connector connector = new Connector();
		connector.setPort(9090);
		tomcat.setConnector(connector);
		tomcat.setHostname("127.0.0.1");
		File baseDir =  TomcatUtil.createTempDir("tomcat");
		tomcat.setBaseDir(baseDir.getAbsolutePath());
		File docBase = TomcatUtil.createTempDir("tomcat-docbase");
		tomcat.addWebapp("",docBase.getAbsolutePath());
		tomcat.getHost().setAutoDeploy(false);
		tomcat.start();
		tomcat.getServer().await();
	}

	/**
	 * RequestMappingHandlerMapping
	 * 是通过 WebMvcConfigurationSupport 的 方法引入的
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#requestMappingHandlerMapping(ContentNegotiationManager, FormattingConversionService, ResourceUrlProvider)
	 *
	 * 用于处理 @Controller 和 @RequestMapping 的
	 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping#isHandler(Class)
	 *
	 * 请求URL和方法映射是通过 父类的 后置处理方法来完成映射的
	 * @see AbstractHandlerMethodMapping#afterPropertiesSet()
	 * 至于RequestMappingHandlerMapping的后置方法会完成一些配置
	 * @see RequestMappingHandlerMapping#afterPropertiesSet()
	 *
	 * ##### 映射关系建立
	 * 1.首先从spring容器中取出所有的bean
	 * @see AbstractHandlerMethodMapping#initHandlerMethods()
	 *A
	 * 2.如果当前bean是匹配的那么会进行处理
	 * @see AbstractHandlerMethodMapping#detectHandlerMethods(Object)
	 *
	 * 2.1 首先获取真实目标类，因为代理类是不包含注解信息的
	 *
	 * 2.2 获取目标类中所有的方法，对每个方法调用 getMappingForMethod() 进行处理
	 * 此时会获取到 Method - RequestMappingInfo 的映射 Map
	 * @see RequestMappingHandlerMapping#getMappingForMethod(Method, Class)
	 * 此时会返回一个 RequestMappingInfo
	 * @see org.springframework.web.servlet.mvc.method.RequestMappingInfo
	 * 每个 RequestMappingInfo 对应一个方法并关联了 beanName 和 bean工厂 ，请求适配条件等
	 *
	 * 2.3 遍历上述的映射进行注册到 父类的mappingRegistry 实例属性中
	 * @see AbstractHandlerMethodMapping#registerHandlerMethod(Object, Method, Object)
	 * @see AbstractHandlerMethodMapping#mappingRegistry
	 *
	 * 调用内部类 MappingRegistry的 register()
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.MappingRegistry#register(Object, Object, Method)
	 * 首先会获取写锁，将beanName(为了支持Scope所以不能直接用bean) 和 beanFactory 处理的Method 封装为 HandlerMethod
	 * @see org.springframework.web.method.HandlerMethod
	 *
	 * 再获取URL，然后注册每个URL和RequestMappingInfo实例的映射关系到 pathLookup属性
	 * @see AbstractHandlerMethodMapping.MappingRegistry#pathLookup
	 *
	 * 注册 HandlerMethod 和 跨域配置的映射到 corsLookup属性
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.MappingRegistry#corsLookup
	 *
	 * 注册RequestMappingInfo 和 封装了 HandlerMethod的 MappingRegistration实例的关系到 registry(HashMap)属性
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.MappingRegistration
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.MappingRegistry#registry
	 *
	 * 2.4 TODO 更新条件，调用完父类注册后会执行自己的 更新消费参数 方法
	 * @see RequestMappingHandlerMapping#registerHandlerMethod(Object, Method, RequestMappingInfo)
	 * @see RequestMappingHandlerMapping#updateConsumesCondition(RequestMappingInfo, Method)
	 * 具体就是处理每个方法的参数，如果
	 *
	 * ###### 请求处理
	 * 前面会走servlet逻辑最终调用这个处理请求
	 * @see org.springframework.web.servlet.DispatcherServlet#doDispatch(HttpServletRequest, HttpServletResponse)
	 *
	 * 1.首先会根据请求的URL判断是否被RequestMappingHandlerMapping支持
	 * @see AbstractHandlerMethodMapping#getHandler(HttpServletRequest)
	 *
	 * 1.1 调用 RequestMapping 获取到之前缓存的HandlerMethod
	 * @see RequestMappingHandlerMapping#getHandlerInternal(HttpServletRequest)
	 * 最终是从 AbstrctHandlerMethodMapping 中获取
	 * @see AbstractHandlerMethodMapping#getHandlerInternal(HttpServletRequest)
	 *
	 * 1.1.1 首先获取读锁 从缓存中获取URL匹配的 RequestMappingInfo
	 * @see AbstractHandlerMethodMapping#lookupHandlerMethod(String, HttpServletRequest)
	 * 最终是从 MappingRegistry 的 pathLookup缓存中获取的
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.MappingRegistry#getMappingsByDirectPath(String)
	 *
	 * 1.1.2 找到匹配的RequestMappingInfo
	 * 再次进行匹配,这次会进行请求级别的匹配 即判断Condition 是否满足
	 * @see AbstractHandlerMethodMapping#addMatchingMappings(Collection, List, HttpServletRequest)
	 * 如果上步找到多个的话会找到最适合的那个，TODO 匹配规则
	 * 找到最合适的会在Request的属性中设置指定值
	 * 并且返回处理请求的具体方法 HandlerMethod
	 *
	 * 1.2 获取到HandlerMethod后 获取支持当前 HandlerMethod 的 拦截器
	 * @see AbstractHandlerMethodMapping#getHandlerExecutionChain(Object, HttpServletRequest)
	 * 并将拦截器和HandlerMethod 封装成 HandlerExectionChain
	 * @see org.springframework.web.servlet.HandlerExecutionChain
	 *
	 * 此时获取到URL对应的方法以及使用的拦截器链
	 *
	 * 2. 获取支持当前处理方法的 Adapter ,需要 Adapter处理方法参数后 调用具体的方法并返回值
	 * @see org.springframework.web.servlet.DispatcherServlet#getHandlerAdapter(Object)
	 *
	 * 2.1 判断 RequestMappingHandlerAdapter是否支持处理 这个方法 就是判断当前是不是 HandlerMethod
	 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter#supports(Object)
	 *
	 * 3. 调用方法之前调用前置拦截器
	 * @see org.springframework.web.servlet.HandlerExecutionChain#applyPreHandle(HttpServletRequest, HttpServletResponse)
	 * 如果前置拦截器返回false,那么会执行拦截器的处理完成方法
	 * 但不会真正调用方法
	 * @see org.springframework.web.servlet.HandlerExecutionChain#triggerAfterCompletion(HttpServletRequest, HttpServletResponse, Exception)
	 *
	 * 4. 调用真正的方法并且获取返回参数
	 * @see org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter#handle(HttpServletRequest, HttpServletResponse, Object)
	 * 具体子类的实现
	 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter#handleInternal(HttpServletRequest, HttpServletResponse, HandlerMethod)
	 *
	 * 4.1 反射调用方法获取返回数据
	 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter#invokeHandlerMethod(HttpServletRequest, HttpServletResponse, HandlerMethod)
	 * 最终会调用 ServletInvocableHandlerMethod 的 invokeAndHandle()
	 * @see org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod#invokeAndHandle(ServletWebRequest, ModelAndViewContainer, Object...)
	 *
	 * 4.1.1 调用方法获取返回值
	 * @see org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod#invokeForRequest(NativeWebRequest, ModelAndViewContainer, Object...)
	 *
	 * 4.1.1.1 先封装方法调用的参数
	 * @see org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod#getMethodArgumentValues(NativeWebRequest, ModelAndViewContainer, Object...)
	 * 对于不同参数调用不同的解析器,此时会将满足的解析器和参数类型进行缓存
	 * @see org.springframework.web.method.support.HandlerMethodArgumentResolverComposite#supportsParameter(MethodParameter) 
	 * @see org.springframework.web.method.support.HandlerMethodArgumentResolverComposite#getArgumentResolver(MethodParameter)
	 * 会调用每个具体解析器是否支持处理这个类型的参数,例如解析 @RequestBody的 RequestResponseBodyMethodProcessor
	 * @see org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor#supportsParameter(MethodParameter)
	 *
	 * 4.1.1.2 反射调用方法
	 * @see org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod#doInvoke(Object...)
	 *
	 * 4.2 调用方法成功
	 * 首先会设置需要进行视图渲染,因为此时不知道是否会直接用response输出响应
	 * mavContainer.setRequestHandled(false);
	 *
	 * 4.2.1 调用返回类型解析器综合类
	 * @see org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite#handleReturnValue(Object, MethodParameter, ModelAndViewContainer, NativeWebRequest)
	 *
	 * 4.2.1.1 首先获取处理当前返回类型的解析
	 * @see org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite#selectHandler(Object, MethodParameter)
	 *
	 * 此时会调用每个具体的 HandlerMethodReturnValueHandler 来判断是否支持处理
	 * @see org.springframework.web.method.support.HandlerMethodReturnValueHandler#supportsReturnType(MethodParameter)
	 * 例如 RequestResponseBodyMethodProcessor 会判断方法返回参数被 @ResponseBody标记
	 * @see org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor#supportsReturnType(MethodParameter)
	 *
	 * 4.2.1.2 调用具体的返回类型解析器解析方法返回数据，
	 * 例如处理 @ResponseBody 的
	 * @see org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor#handleReturnValue(Object, MethodParameter, ModelAndViewContainer, NativeWebRequest)
	 * 设置 mavContainer.setRequestHandled(true) 表示已经处理，不需要进行视图渲染
	 * 调用对用的消息解析器进行返回数据的输出
	 * @see AbstractMessageConverterMethodProcessor#writeWithMessageConverters(java.lang.Object, org.springframework.core.MethodParameter, org.springframework.http.server.ServletServerHttpRequest, org.springframework.http.server.ServletServerHttpResponse)
	 * 如果是 CharSequence 会转为 String,
	 * 不是 CharSequence,如果返回值不为null，那么用返回值的类型否则用方法的类型
	 * 最后调用每个 消息转换器 ，找到支持写出返回类型的消息转换器
	 * @see org.springframework.http.converter.GenericHttpMessageConverter#canWrite(Type, Class, MediaType)
	 * 在写出消息之前需要调用 @ResponseBodyAdvice 标记的 bean 处理消息
	 * @see org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyAdviceChain#beforeBodyWrite(Object, MethodParameter, MediaType, Class, ServerHttpRequest, ServerHttpResponse)
	 * 如果处理后的数据不为null 那么会调用具体的消息转换器将消息写出
	 * 比如默认的 jackson
	 * @see org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter
	 *
	 *
	 * 对于 ViewNameMethodReturnValueHandler 会进行视图渲染
	 * @see org.springframework.web.servlet.mvc.method.annotation.ViewNameMethodReturnValueHandler#handleReturnValue(Object, MethodParameter, ModelAndViewContainer, NativeWebRequest)
	 *
	 *
	 *
	 */
	@Test
	public void testRequestMappingHandlerMapping() throws Exception{
		Tomcat tomcat = new Tomcat();
		Connector connector = new Connector();
		connector.setPort(9091);
		tomcat.setConnector(connector);
		tomcat.setHostname("127.0.0.1");
		File baseDir =  TomcatUtil.createTempDir("tomcat");
		tomcat.setBaseDir(baseDir.getAbsolutePath());
		File docBase = TomcatUtil.createTempDir("tomcat-docbase");
		tomcat.addWebapp("",docBase.getAbsolutePath());
		tomcat.getHost().setAutoDeploy(false);
		tomcat.start();
		tomcat.getServer().await();
	}


}
