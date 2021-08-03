package com.kanozz.beandefinition.factorymethod;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestFactoryMethod {

	/**
	 *
	 * 指定factoryBeanName和factoryMethodName会使用指定的来创建bean
	 * @see BeanDefinition#setFactoryBeanName(String)
	 * @see BeanDefinition#setFactoryMethodName(String)
	 *
	 * 如果同时指定那么需要的是实例方法
	 *
	 */
	@Test
	public void testHaveBeanAndMethod() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.registerBeanDefinition("kanoA", new RootBeanDefinition(KanoA.class));

		BeanDefinition kanoB = BeanDefinitionBuilder.genericBeanDefinition(KanoB.class)
				.setFactoryMethodOnBean("createKanoB","kanoA").getBeanDefinition();
		context.registerBeanDefinition("kanoB",kanoB);
		context.refresh();
	}


	/**
	 *
	 * 如果没有指定factoryMethodName的话
	 * 指定的factoryBean不存在的情况下回直接忽略
	 * 指定了factoryBean但没有指定method也会直接忽略
	 */
	@Test
	public void testJustBean() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.registerBeanDefinition("kanoA", new RootBeanDefinition(KanoA.class));

		/**
		 * 需要配套使用
		 */
//		BeanDefinition kanoB = BeanDefinitionBuilder.genericBeanDefinition(KanoB.class)
//				.setFactoryMethodOnBean("","kano").getBeanDefinition();
//
//		context.registerBeanDefinition("kanoB",kanoB);

		BeanDefinition bd = new RootBeanDefinition(KanoB.class);
		bd.setFactoryBeanName("kanoA");
		context.registerBeanDefinition("kanoB",bd);
		context.refresh();
	}

	/**
	 *
	 * 如果只指定factoryMethod,那么会默认使用
	 * 当前创建bean的静态方法去创建bean
	 * 因为非静态方法需要先有对象和创建bean的
	 * 过程相冲突
	 *
	 */
	@Test
	public void testJustMethod() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.registerBeanDefinition("kanoA", new RootBeanDefinition(KanoA.class));

		BeanDefinition kanoB = BeanDefinitionBuilder.genericBeanDefinition(KanoB.class)
				.setFactoryMethod("createKanoB").getBeanDefinition();

		context.registerBeanDefinition("kanoB",kanoB);
		context.refresh();
	}

}
