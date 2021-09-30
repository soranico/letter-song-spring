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
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.lang.reflect.Method;

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
}
