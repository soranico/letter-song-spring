package com.kanozz.bpp.priority;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@EnableAspectJAutoProxy
class KanoAspect {

	@Before("execution(* com.kanozz.bpp.priority.*.*(..))")
	public void before(){
//		log.info("before run method");
	}

}

