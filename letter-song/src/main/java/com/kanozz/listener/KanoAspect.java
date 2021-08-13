package com.kanozz.listener;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@Slf4j
public class KanoAspect {
	@Before("execution(* com.kanozz.listener.*.*(..))")
	public void before(){
		log.info("before run method");
	}
}
