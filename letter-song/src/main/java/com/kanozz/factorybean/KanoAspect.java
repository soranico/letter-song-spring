package com.kanozz.factorybean;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class KanoAspect {
	private static final Logger log = LoggerFactory.getLogger(KanoAspect.class);
	@Before("execution(* com.kanozz.factorybean.*.*(..))")
	public void before(){
		log.info("before run method");
	}
}
