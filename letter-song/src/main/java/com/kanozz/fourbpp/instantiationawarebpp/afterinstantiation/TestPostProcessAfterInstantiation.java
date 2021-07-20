package com.kanozz.fourbpp.instantiationawarebpp.afterinstantiation;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class TestPostProcessAfterInstantiation {

	/**
	 *
	 * 1. 在显示注入和自动注入模型下均不会注入AfterA的属性
	 *
	 * 2.AfterB只能通过构造方法注入
	 *
	 * 3.对于只有一个参数的构造无论是否注入都不会影响,因为在指明是否注入之前，对象已经创建了
	 *
	 *
	 */
	@Test
	public void testPostProcessAfterInstantiation(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.getBeanFactory().addBeanPostProcessor(AfterInstantiationBpp.INSTANCE);
		context.addBeanFactoryPostProcessor((beanFactory)->{
			AnnotatedGenericBeanDefinition afterBppA = (AnnotatedGenericBeanDefinition) beanFactory.getBeanDefinition("afterBppA");
			afterBppA.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);

			AnnotatedGenericBeanDefinition afterBppB = (AnnotatedGenericBeanDefinition) beanFactory.getBeanDefinition("afterBppB");
			afterBppB.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);

			AnnotatedGenericBeanDefinition afterBppC = (AnnotatedGenericBeanDefinition) beanFactory.getBeanDefinition("afterBppC");
			afterBppC.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);


		});
		context.register(AfterBppA.class, AfterBppB.class,AfterBppC.class);
		context.refresh();
		log.info("{}",context.getBean(AfterBppA.class).toString());
		log.info("{}",context.getBean(AfterBppB.class).toString());
		log.info("{}",context.getBean(AfterBppC.class).toString());

	}
}
