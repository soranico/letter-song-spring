package com.kanosd.construct;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class TestConstruct {

	@Test
	public void testConstruct() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(KanoA.class, KanoB.class, KanoC.class);
		context.addBeanFactoryPostProcessor(beanFactory -> {
			AnnotatedGenericBeanDefinition kanoA = (AnnotatedGenericBeanDefinition) beanFactory.getBeanDefinition("kanoA");
			kanoA.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
		});
		context.refresh();

		KanoA kanoA = context.getBean(KanoA.class);
		log.info("kanoA = {}",kanoA.toString());
	}


	@Test
	public void testConstructWithBean() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register( KanoB.class, KanoC.class,KanoConfig.class);
		context.refresh();
		KanoD kanoD = context.getBean(KanoD.class);
		log.info("kanoD = {}",kanoD.toString());
	}
}
