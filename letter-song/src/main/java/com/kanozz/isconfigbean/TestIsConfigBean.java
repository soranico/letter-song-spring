package com.kanozz.isconfigbean;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestIsConfigBean {


	@Test
	public void testIsConfigBean(){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(IsConfigKanoA.class,IsConfigKanoB.class,IsConfigKanoC.class);
		context.refresh();
		
	}
}
