package com.kanozz.mvc.zero;

import com.kanozz.mvc.TomcatUtil;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.junit.Test;

import javax.servlet.ServletContext;
import java.io.File;

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
	 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#onStartup(ServletContext)
	 * 在onStartUp()里面创建了这两个对象
	 *
	 *
	 * @throws Exception
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
