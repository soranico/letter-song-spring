package com.kanozz.lazy;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class TestLazyInject {


	@Test
	public void testLazy(){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(LazyKanoA.class,LazyKanoB.class,LazyKanoC.class);
		context.refresh();
		LazyKanoC lazyKanoC = context.getBean(LazyKanoC.class);
		LazyKanoA lazyKanoA = context.getBean(LazyKanoA.class);

		log.info("LazyKanoB same = {}",
				lazyKanoA.getLazyKanoB() == lazyKanoC.getLazyKanoB());
	}
}
