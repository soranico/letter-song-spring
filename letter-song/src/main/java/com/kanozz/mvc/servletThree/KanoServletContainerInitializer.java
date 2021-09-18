package com.kanozz.mvc.servletThree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

@HandlesTypes(KanoHandlerType.class)
public class KanoServletContainerInitializer implements ServletContainerInitializer {

	private static final Logger log = LoggerFactory.getLogger(KanoServletContainerInitializer.class);



	@Override
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		log.info("handleType = {}",c);
	}
}
