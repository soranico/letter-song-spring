package com.kanozz.proxy.jdk;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class KanoAspect {

	private static final Logger log = LoggerFactory.getLogger(KanoAspect.class);

	@Before("execution(* com.kanozz.proxy.jdk.KanoJdkImpl.kano01())")

	public void before(){
		log.info("before run method");
	}

	@Pointcut("execution(* com.kanozz.proxy.jdk.KanoJdkImpl.kano02())")
	public void pointCut(){
		log.info("pointCut");
	}

	@Before("com.kanozz.proxy.jdk.KanoAspect.pointCut()")
	public void beforePointCut(){
		log.info("beforePointCut");
	}


}
