package com.kanozz.listener;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.kanozz.listener")
@Aspect
@Slf4j
@EnableAspectJAutoProxy
class KanoConfiguration {

	@Before("execution(* com.kanozz.listener.*.*(..))")
	public void before(){
		log.info("before run method");
	}
}
