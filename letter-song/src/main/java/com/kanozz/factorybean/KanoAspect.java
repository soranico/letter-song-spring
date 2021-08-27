package com.kanozz.factorybean;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@Slf4j
public class KanoAspect {
	@Before("execution(* com.kanozz.factorybean.*.*(..))")
	public void before(){
		log.info("before run method");
	}
}
