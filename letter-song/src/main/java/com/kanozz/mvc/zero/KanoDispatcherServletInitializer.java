package com.kanozz.mvc.zero;

import com.kanozz.mvc.zero.mvc.KanoMvcEnvironment;
import com.kanozz.mvc.zero.spring.KanoSpringEnvironment;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class KanoDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


	@Override
	protected String[] getServletMappings() {
		return new String[]{
				"/"
		};
	}

	/**
	 * spring 环境
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{
				KanoSpringEnvironment.class
		};
	}

	/**
	 * mvc 环境
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{
				KanoMvcEnvironment.class
		};
	}
}
