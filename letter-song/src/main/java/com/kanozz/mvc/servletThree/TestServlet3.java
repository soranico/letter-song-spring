package com.kanozz.mvc.servletThree;

import com.kanozz.mvc.TomcatUtil;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.junit.Test;

import javax.servlet.annotation.HandlesTypes;
import java.io.File;

public class TestServlet3 {


	/**
	 * @see org.apache.catalina.startup.ContextConfig#lifecycleEvent(LifecycleEvent)
	 * @see org.apache.catalina.Lifecycle#CONFIGURE_START_EVENT
	 *
	 * Servlet3.0规范在容器启动时会加载META-INF/services路径下的
	 * javax.servlet.ServletContainerInitalizer的实现
	 * 对于spring mvc 默认加载了 SpringServletContaierInitializer
	 * @see org.springframework.web.SpringServletContainerInitializer
	 *
	 * 对于 @HandlerType 注解而言会收集指定的接口所有实现
	 * @see HandlesTypes
	 * 并放入 onStartUp(）的形参中
	 */
	@Test
	public void testServlet3() throws Exception{
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
