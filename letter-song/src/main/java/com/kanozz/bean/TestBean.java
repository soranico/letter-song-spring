package com.kanozz.bean;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestBean {

	@Test
	public void testStatic(){

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(KanoConfig.class);
		context.refresh();

	}
}
