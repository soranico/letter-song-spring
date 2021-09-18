package com.kanozz.listener;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.kanozz.listener")
@Aspect
@EnableAspectJAutoProxy
class KanoConfiguration {
	private static final Logger log = LoggerFactory.getLogger(KanoConfiguration.class);
	@Before("execution(* com.kanozz.listener.*.*(..))")
	public void before(){
		log.info("before run method");
	}
}
