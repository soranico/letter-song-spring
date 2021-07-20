package com.kanozz.beanfactory.ingore;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class TestIgnoreWithAutowire {

	/**
	 * spring默认的bean注册模型为
	 * {@link org.springframework.beans.factory.config.AutowireCapableBeanFactory.AUTOWIRE_NO}
	 * 即默认不自动注入
	 *
	 * ignoreDependencyInterface()忽略注入类型的前提是自动注入
	 * 因此默认情况下,不会生效,需要修改 kanoA的注入模型
	 */
	@Test
	public void testIgnoreDependencyType(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(KanoA.class, KanoB.class);
		context.addBeanFactoryPostProcessor(ChangeAutowireModelBeanPostProcessor.INSTANCE);
		context.refresh();
		KanoA kanoA = context.getBean(KanoA.class);
		log.info("kanoA = {}",kanoA);
	}


}
