package com.kanozz.staticBean;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestStaticBean {
	private static final Logger log = LoggerFactory.getLogger(TestStaticBean.class);
	@Test
	public void testBFPP(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(KanoConfigWithBFPP.class);
		context.refresh();
		log.info("{}",context.getBean(KanoConfigWithBFPP.class));

	}

	@Test
	public void testBDRPP(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(KanoConfigWithBDFPP.class);
		context.refresh();
		log.info("{}",context.getBean(KanoConfigWithBDFPP.class));

	}
}
