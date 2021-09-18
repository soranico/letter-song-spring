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

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{
				KanoSpringEnvironment.class
		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{
				KanoMvcEnvironment.class
		};
	}
}
