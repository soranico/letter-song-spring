package com.kanozz.lazy;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestLazyInject {
	private static final Logger log = LoggerFactory.getLogger(TestLazyInject.class);

	@Test
	public void testLazy(){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(LazyKanoA.class,LazyKanoB.class,LazyKanoC.class);
		context.refresh();
		LazyKanoC lazyKanoC = context.getBean(LazyKanoC.class);
		LazyKanoA lazyKanoA = context.getBean(LazyKanoA.class);

//		log.info("LazyKanoB same = {}",
//				lazyKanoA.getLazyKanoB() == lazyKanoC.getLazyKanoB());
	}
}
