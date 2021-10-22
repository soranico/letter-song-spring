package com.kanozz.proxy.cglib;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class KanoAspect {

	private static final Logger log = LoggerFactory.getLogger(KanoAspect.class);

	@Before("execution(* com.kanozz.proxy.cglib.KanoCglib.before())")
	public void before(){
		log.info("before run method");
	}

//	@Pointcut("execution(* com.kanozz.proxy.cglib.KanoCglib.kano02())")
//	public void pointCut(){
//		log.info("pointCut");
//	}
//
//	@Before("com.kanozz.proxy.cglib.KanoAspect.pointCut()")
//	public void beforePointCut(){
//		log.info("beforePointCut");
//	}


	@Around("execution(* com.kanozz.proxy.cglib.KanoCglib.around())")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable{
		log.info("before run method");
		joinPoint.proceed();
	}


	@After("execution(* com.kanozz.proxy.cglib.KanoCglib.after())")
	public void after() throws Throwable{
		log.info("before run method");
	}

	@AfterReturning("execution(* com.kanozz.proxy.cglib.KanoCglib.afterReturning())")
	public void afterReturning() throws Throwable{
		log.info("before run method");
	}

	@AfterThrowing("execution(* com.kanozz.proxy.cglib.KanoCglib.throwing())")
	public void afterThrowing() throws Throwable{
		log.info("before run method");
	}



	@Before("execution(* com.kanozz.proxy.cglib.KanoPrototypeCglib.kano01())")
	public void beforePrototype(){
		log.info("before run method");
	}

}
