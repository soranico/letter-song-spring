package com.kanozz.beanfactory.clear.metacache;

import com.kanozz.beanfactory.clear.metacache.bfpp.ForceFactoryBeanFactoryPostProcessor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestClearCache {

	private static final Logger log = LoggerFactory.getLogger(TestClearCache.class);
	/**
	 *
	 * 1. 处理器设置
	 * 	kanoA.setScope("prototype");
	 * KanoA为原型不会初始化
	 * 2. 处理器设置
	 * 	beanFactory.freezeConfiguration();
	 * 	kanoA.setScope("prototype");
	 * 因为在设置KanoA之前已经冻结了配置,后续对BD的操作
	 * 不会再合并(仅对本次创建生效)
	 *
	 *
	 */
	@Test
	public void testClearCache() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.kanozz.beanfactory.clear.metacache");
		context.addBeanFactoryPostProcessor(ForceFactoryBeanFactoryPostProcessor.INSTANCE);
		// 初次会创建,因为还是单例
		context.refresh();
		// 每次获取都会重新创建一个
		Object beforeKanoA = context.getBean("kanoA");
		Object afterKanoA = context.getBean("kanoA");
		log.info("same kanoA = {}",beforeKanoA == afterKanoA);
		context.registerBeanDefinition("kanoA",new RootBeanDefinition(KanoD.class));
	}

	@Test
	public void testForceAndRegisterBD() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.kanozz.beanfactory.clear.metacache");
		context.refresh();
		Object beforeKanoA = context.getBean("kanoA");
		context.registerBeanDefinition("kanoA",new RootBeanDefinition(KanoD.class));
		Object afterKanoA = context.getBean("kanoA");
		context.registerBeanDefinition("kanoA",new RootBeanDefinition(KanoA.class));
		Object reRegisterKanoA = context.getBean("kanoA");
		log.info("same kanoA = {}",beforeKanoA == afterKanoA);
	}



}
