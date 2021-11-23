package com.kanozz.cycle;

import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestCycle {


	@Test
	public void testCycle(){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(KanoA.class,KanoB.class,KanoAspect.class);
		context.register(AnnotationAwareAspectJAutoProxyCreator.class);
		context.refresh();
	}
}
