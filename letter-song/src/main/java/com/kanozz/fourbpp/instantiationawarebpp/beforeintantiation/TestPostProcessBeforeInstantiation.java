package com.kanozz.fourbpp.instantiationawarebpp.beforeintantiation;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestPostProcessBeforeInstantiation {

	@Test
	public void testPostProcessBeforeInstantiation(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.getBeanFactory().addBeanPostProcessor(BeforeInstantiationBpp.INSTANCE);
		context.register(BeforeBppA.class,BeforeBppB.class);
		context.refresh();
		context.getBean(BeforeBppA.class);
	}
}
