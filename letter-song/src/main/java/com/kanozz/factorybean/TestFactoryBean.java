package com.kanozz.factorybean;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestFactoryBean {


	@Test
	public void testProxy(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(KanoFactoryBeanA.class,KanoAspect.class);
//		context.register(AnnotationAwareAspectJAutoProxyCreator.class);
		context.refresh();
		context.getBean(KanoA.class).kano();
		context.getBean("&kanoFactoryBeanA");
	}
}
