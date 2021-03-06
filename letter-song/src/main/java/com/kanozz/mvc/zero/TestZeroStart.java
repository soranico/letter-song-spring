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
import org.springframework.web.bind.support.WebDataBinderFactory;
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
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
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
	 * ??????web.xml
	 *
	 * <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	 * @see org.springframework.web.context.ContextLoaderListener
	 * ???????????????????????? ????????? spring ??????
	 *
	 * <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	 *
	 * ???????????????Servlet??? ?????? spring mvc ??????
	 *
	 * ?????? servlet 3.0?????? ????????????????????? WebApplicationInitializer
	 * ???????????? ?????????????????????????????????????????????
	 * @see org.springframework.web.SpringServletContainerInitializer
	 *
	 * ???spring mvc????????????????????????
	 * @see AbstractDispatcherServletInitializer#onStartup(javax.servlet.ServletContext)
	 * ???onStartUp()??????????????????????????????
	 *
	 * ?????????????????????????????????????????????????????????SPI???????????????????????????????????? onStartUp()
	 *
	 * 1. spring??????
	 *
	 * 1.1
	 * ??? AbstractContextLoaderInitializer ???????????????spring??????????????????????????? ContextLoaderListener
	 * ???????????????????????????????????????????????????
	 * @see AbstractContextLoaderInitializer#onStartup(javax.servlet.ServletContext)
	 *
	 * ???????????? ????????????????????????????????????????????????spring??????
	 * @see AbstractAnnotationConfigDispatcherServletInitializer#createRootApplicationContext()
	 * @see org.springframework.web.context.support.AnnotationConfigWebApplicationContext
	 *
	 * ????????????????????????????????????????????????????????????????????????????????????????????????spring?????????
	 * @see AbstractAnnotationConfigDispatcherServletInitializer#getRootConfigClasses()
	 *
	 *
	 * 1.2
	 * ???web????????????????????????Listener??? contextInitialized()????????????????????????spring??????????????????
	 * ?????????spring??????????????? web??????????????????
	 * @see org.springframework.web.context.ContextLoaderListener#contextInitialized(ServletContextEvent)
	 *
	 * ??????spring????????????
	 *
	 *
	 * 2. mvc??????
	 *
	 * 2.1 ???1.2??????????????????????????????
	 * ???1.1?????????????????????????????? AbstractDispatcherServletInitalizer ??? onStartup
	 * @see AbstractDispatcherServletInitializer#onStartup(ServletContext)
	 *
	 * ???????????? registerDispatcherServlet()?????????mvc??????
	 * @see AbstractDispatcherServletInitializer#registerDispatcherServlet(ServletContext)
	 *
	 * ?????????????????????????????????createServletApplicationContext()
	 * @see AbstractAnnotationConfigDispatcherServletInitializer#createServletApplicationContext()
	 * @see org.springframework.web.context.support.AnnotationConfigWebApplicationContext
	 * ??????????????????????????????mvc??????????????????????????????
	 *
	 * ?????????DispatcherServlet ?????????mvc ?????????????????????web??????????????????init()
	 *
	 * 2.2
	 * web????????????servlet???init()
	 * @see org.springframework.web.servlet.HttpServletBean#init()
	 * ?????? FrameworkServlet??? initServletBean() ?????? spring?????????mvc???????????????
	 * ???????????? mvc??????????????????
	 * @see FrameworkServlet#initServletBean()
	 * @see FrameworkServlet#initWebApplicationContext()
	 *
	 * ???????????? configureAndRefreshWebApplicationContext() ???????????????????????? ????????????
	 * mvc??????????????????????????????,?????????????????????spring???????????????
	 * web???????????????Listener????????????servlet
	 * @see FrameworkServlet#configureAndRefreshWebApplicationContext(ConfigurableWebApplicationContext)
	 *
	 * ?????? ContextRefreshedEvent
	 * @see FrameworkServlet.ContextRefreshListener#onApplicationEvent(ContextRefreshedEvent)
	 * ???????????????????????????????????????????????????????????????????????????????????????????????????
	 * @see SourceFilteringListener#onApplicationEvent(ApplicationEvent)
	 *
	 * 2.3
	 * mvc???????????????????????????????????????????????? FrameworkServlet???onApplicationEvent()
	 * @see FrameworkServlet#onApplicationEvent(ContextRefreshedEvent)
	 *
	 * ??????????????? DispatcherServlet???onRefresh() ????????????????????? HanlderMapping HandlerAdpater HandlerExceptionResolver
	 * bean?????????
	 * @see org.springframework.web.servlet.DispatcherServlet#onRefresh(ApplicationContext)
	 * @see org.springframework.web.servlet.DispatcherServlet#initStrategies(ApplicationContext)
	 *
	 *
	 * ??????bean???????????????????????? @EnableWebMvc ??????????????? DelegatingWebMvcConfiguration ????????????
	 * @see org.springframework.web.servlet.config.annotation.EnableWebMvc
	 * @see org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration
	 * ???????????? WebMvcConfigurationSupport ????????????bean
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
	 *
	 * e.g HandlerMapping
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#requestMappingHandlerMapping(ContentNegotiationManager, FormattingConversionService, ResourceUrlProvider)
	 *
	 *
	 * 3. ????????????
	 *
	 * 3.1
	 * ???????????? FrameworkServlet??? servic() ,????????????patch????????????????????????????????????
	 * @see FrameworkServlet#service(HttpServletRequest, HttpServletResponse)
	 * @see org.springframework.web.servlet.HttpServletBean#service(HttpServletRequest, HttpServletResponse)
	 *
	 * ????????????doGet() ,doPost()
	 * @see FrameworkServlet#doPost(HttpServletRequest, HttpServletResponse) 
	 * ?????? processRequest()???request ??? response ??????ThreadLocal
	 * @see FrameworkServlet#processRequest(HttpServletRequest, HttpServletResponse)
	 *
	 *
	 * ???????????? initContextHolders() ????????? ThreadLocal
	 * @see FrameworkServlet#initContextHolders(HttpServletRequest, LocaleContext, RequestAttributes)
	 * @see org.springframework.web.context.request.RequestContextHolder#setRequestAttributes(RequestAttributes, boolean)
	 *
	 * 3.2 
	 * ?????? DispatcherServlet??? doDispatch() ????????????
	 * @see org.springframework.web.servlet.DispatcherServlet#doService(HttpServletRequest, HttpServletResponse)
	 * @see org.springframework.web.servlet.DispatcherServlet#doDispatch(HttpServletRequest, HttpServletResponse)
	 *
	 * 3.2.1 ??????????????????
	 * @see org.springframework.web.servlet.DispatcherServlet#checkMultipart(HttpServletRequest)
	 *
	 * 3.2.2 ????????????url ??? ???????????????????????????
	 * ??????????????? HandlerMapping ?????????????????????
	 * @see org.springframework.web.servlet.DispatcherServlet#getHandler(HttpServletRequest)
	 * 
	 * ????????????????????????HandlerMapping?????????????????? ???????????? RequestMappingHandlerMapping
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMapping#getHandler(HttpServletRequest)
	 *
	 * ????????????????????????????????? getHandlerInternal() ??????????????????url??????????????????????????????????????????????????????
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping#getHandlerInternal(HttpServletRequest)
	 *
	 * ?????? lookupHandlerMethod() ??????????????????
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping#lookupHandlerMethod(String, HttpServletRequest)
	 * ????????????????????? url ?????????
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.MappingRegistry#getMappingsByDirectPath(String)
	 * url?????????????????????????????? HandlerMapping ????????????????????????
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping#afterPropertiesSet()
	 * ???????????????????????????
	 * @see AbstractHandlerMethodMapping#initHandlerMethods()
	 * ????????????????????????????????? AbstractHandlerMethodMapping ??? detectHandlerMethods() detect:??????
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping#detectHandlerMethods(Object)
	 * ?????????
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
	 * ????????? WebMvcConfigurationSupport ??? ???????????????
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#requestMappingHandlerMapping(ContentNegotiationManager, FormattingConversionService, ResourceUrlProvider)
	 *
	 * ???????????? @Controller ??? @RequestMapping ???
	 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping#isHandler(Class)
	 *
	 * ??????URL???????????????????????? ????????? ????????????????????????????????????
	 * @see AbstractHandlerMethodMapping#afterPropertiesSet()
	 * ??????RequestMappingHandlerMapping????????????????????????????????????
	 * @see RequestMappingHandlerMapping#afterPropertiesSet()
	 *
	 * ##### ??????????????????
	 * 1.?????????spring????????????????????????bean
	 * @see AbstractHandlerMethodMapping#initHandlerMethods()
	 *A
	 * 2.????????????bean?????????????????????????????????
	 * @see AbstractHandlerMethodMapping#detectHandlerMethods(Object)
	 *
	 * 2.1 ????????????????????????????????????????????????????????????????????????
	 *
	 * 2.2 ????????????????????????????????????????????????????????? getMappingForMethod() ????????????
	 * ?????????????????? Method - RequestMappingInfo ????????? Map
	 * @see RequestMappingHandlerMapping#getMappingForMethod(Method, Class)
	 * ????????????????????? RequestMappingInfo
	 * @see org.springframework.web.servlet.mvc.method.RequestMappingInfo
	 * ?????? RequestMappingInfo ?????????????????????????????? beanName ??? bean?????? ????????????????????????
	 *
	 * 2.3 ???????????????????????????????????? ?????????mappingRegistry ???????????????
	 * @see AbstractHandlerMethodMapping#registerHandlerMethod(Object, Method, Object)
	 * @see AbstractHandlerMethodMapping#mappingRegistry
	 *
	 * ??????????????? MappingRegistry??? register()
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.MappingRegistry#register(Object, Object, Method)
	 * ???????????????????????????beanName(????????????Scope?????????????????????bean) ??? beanFactory ?????????Method ????????? HandlerMethod
	 * @see org.springframework.web.method.HandlerMethod
	 *
	 * ?????????URL?????????????????????URL???RequestMappingInfo???????????????????????? pathLookup??????
	 * @see AbstractHandlerMethodMapping.MappingRegistry#pathLookup
	 *
	 * ?????? HandlerMethod ??? ???????????????????????? corsLookup??????
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.MappingRegistry#corsLookup
	 *
	 * ??????RequestMappingInfo ??? ????????? HandlerMethod??? MappingRegistration?????????????????? registry(HashMap)??????
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.MappingRegistration
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.MappingRegistry#registry
	 *
	 * 2.4 TODO ????????????????????????????????????????????????????????? ?????????????????? ??????
	 * @see RequestMappingHandlerMapping#registerHandlerMethod(Object, Method, RequestMappingInfo)
	 * @see RequestMappingHandlerMapping#updateConsumesCondition(RequestMappingInfo, Method)
	 * ????????????????????????????????????????????????
	 *
	 * ###### ????????????
	 * ????????????servlet????????????????????????????????????
	 * @see org.springframework.web.servlet.DispatcherServlet#doDispatch(HttpServletRequest, HttpServletResponse)
	 *
	 * 1.????????????????????????URL???????????????RequestMappingHandlerMapping??????
	 * @see AbstractHandlerMethodMapping#getHandler(HttpServletRequest)
	 *
	 * 1.1 ?????? RequestMapping ????????????????????????HandlerMethod
	 * @see RequestMappingHandlerMapping#getHandlerInternal(HttpServletRequest)
	 * ???????????? AbstrctHandlerMethodMapping ?????????
	 * @see AbstractHandlerMethodMapping#getHandlerInternal(HttpServletRequest)
	 *
	 * 1.1.1 ?????????????????? ??????????????????URL????????? RequestMappingInfo
	 * @see AbstractHandlerMethodMapping#lookupHandlerMethod(String, HttpServletRequest)
	 * ???????????? MappingRegistry ??? pathLookup??????????????????
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.MappingRegistry#getMappingsByDirectPath(String)
	 *
	 * 1.1.2 ???????????????RequestMappingInfo
	 * ??????????????????,???????????????????????????????????? ?????????Condition ????????????
	 * @see AbstractHandlerMethodMapping#addMatchingMappings(Collection, List, HttpServletRequest)
	 * ????????????????????????????????????????????????????????????TODO ????????????
	 * ????????????????????????Request???????????????????????????
	 * ??????????????????????????????????????? HandlerMethod
	 *
	 * 1.2 ?????????HandlerMethod??? ?????????????????? HandlerMethod ??? ?????????
	 * @see AbstractHandlerMethodMapping#getHandlerExecutionChain(Object, HttpServletRequest)
	 * ??????????????????HandlerMethod ????????? HandlerExectionChain
	 * @see org.springframework.web.servlet.HandlerExecutionChain
	 *
	 * ???????????????URL??????????????????????????????????????????
	 *
	 * 2. ????????????????????????????????? Adapter ,?????? Adapter????????????????????? ?????????????????????????????????
	 * @see org.springframework.web.servlet.DispatcherServlet#getHandlerAdapter(Object)
	 *
	 * 2.1 ?????? RequestMappingHandlerAdapter?????????????????? ???????????? ??????????????????????????? HandlerMethod
	 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter#supports(Object)
	 *
	 * 3. ???????????????????????????????????????
	 * @see org.springframework.web.servlet.HandlerExecutionChain#applyPreHandle(HttpServletRequest, HttpServletResponse)
	 * ??????????????????????????? false ,?????????????????????????????????????????????(????????????????????????false????????????????????? ????????????)
	 * ???????????????????????????
	 * @see org.springframework.web.servlet.HandlerExecutionChain#triggerAfterCompletion(HttpServletRequest, HttpServletResponse, Exception)
	 *
	 * 4. ?????????????????????????????????????????????
	 * @see org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter#handle(HttpServletRequest, HttpServletResponse, Object)
	 * ?????????????????????
	 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter#handleInternal(HttpServletRequest, HttpServletResponse, HandlerMethod)
	 *
	 * 4.1 ????????????????????????????????????
	 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter#invokeHandlerMethod(HttpServletRequest, HttpServletResponse, HandlerMethod)
	 * ??????????????? ServletInvocableHandlerMethod ??? invokeAndHandle()
	 * @see org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod#invokeAndHandle(ServletWebRequest, ModelAndViewContainer, Object...)
	 *
	 * 4.1.1 ???????????????????????????
	 * @see org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod#invokeForRequest(NativeWebRequest, ModelAndViewContainer, Object...)
	 *
	 * 4.1.1.1 ??????????????????????????????
	 * @see org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod#getMethodArgumentValues(NativeWebRequest, ModelAndViewContainer, Object...)
	 * ??????????????????????????????????????????,?????????????????????????????????????????????????????????
	 * @see org.springframework.web.method.support.HandlerMethodArgumentResolverComposite#supportsParameter(MethodParameter) 
	 * @see org.springframework.web.method.support.HandlerMethodArgumentResolverComposite#getArgumentResolver(MethodParameter)
	 * ?????????????????????????????????????????????????????????????????????,???????????? @RequestBody??? RequestResponseBodyMethodProcessor
	 * @see org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor#supportsParameter(MethodParameter)
	 * @see org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor#resolveArgument(MethodParameter, ModelAndViewContainer, NativeWebRequest, WebDataBinderFactory) 
	 *
	 * 4.1.1.2 ??????????????????
	 * @see org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod#doInvoke(Object...)
	 *
	 * 4.2 ??????????????????
	 * ???????????????????????????????????????,???????????????????????????????????????response????????????
	 * mavContainer.setRequestHandled(false);
	 *
	 * 4.2.1 ????????????????????????????????????
	 * @see org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite#handleReturnValue(Object, MethodParameter, ModelAndViewContainer, NativeWebRequest)
	 *
	 * 4.2.1.1 ?????????????????????????????????????????????
	 * @see org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite#selectHandler(Object, MethodParameter)
	 *
	 * ?????????????????????????????? HandlerMethodReturnValueHandler ???????????????????????????
	 * @see org.springframework.web.method.support.HandlerMethodReturnValueHandler#supportsReturnType(MethodParameter)
	 * ?????? RequestResponseBodyMethodProcessor ?????????????????????????????? @ResponseBody??????
	 * @see org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor#supportsReturnType(MethodParameter)
	 *
	 * 4.2.1.2 ???????????????????????????????????????????????????????????????
	 * ???????????? @ResponseBody ???
	 * @see org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor#handleReturnValue(Object, MethodParameter, ModelAndViewContainer, NativeWebRequest)
	 * ?????? mavContainer.setRequestHandled(true) ????????????????????????????????????????????????
	 * ?????????????????????????????????????????????????????????
	 * @see AbstractMessageConverterMethodProcessor#writeWithMessageConverters(java.lang.Object, org.springframework.core.MethodParameter, org.springframework.http.server.ServletServerHttpRequest, org.springframework.http.server.ServletServerHttpResponse)
	 * ????????? CharSequence ????????? String,
	 * ?????? CharSequence,?????????????????????null??????????????????????????????????????????????????????
	 * ?????????????????? ??????????????? ???????????????????????????????????????????????????
	 * @see org.springframework.http.converter.GenericHttpMessageConverter#canWrite(Type, Class, MediaType)
	 * ????????????????????????????????? @ResponseBodyAdvice ????????? bean ????????????
	 * @see org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyAdviceChain#beforeBodyWrite(Object, MethodParameter, MediaType, Class, ServerHttpRequest, ServerHttpResponse)
	 *
	 * ??????????????????
	 * @see RequestMappingHandlerAdapter#afterPropertiesSet()
	 *
	 * ??????????????????????????????null ??????????????????????????????????????????????????????
	 * ??????????????? jackson
	 * @see org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter
	 *
	 *
	 * ?????? ViewNameMethodReturnValueHandler ?????????????????????
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
