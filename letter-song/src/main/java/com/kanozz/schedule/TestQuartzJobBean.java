package com.kanozz.schedule;


import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class TestQuartzJobBean {

	@Test
	public void testQuartzJobBean(){
		// jdk
//		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		// cglib
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(QuartzKanoConfig.class,QuartzKanoA.class);
		context.refresh();
		Scanner scanner = new Scanner(System.in);
		scanner.next();

	}
}
