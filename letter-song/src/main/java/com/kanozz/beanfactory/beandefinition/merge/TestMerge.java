package com.kanozz.beanfactory.beandefinition.merge;

import com.kanozz.beanfactory.clear.metacache.bfpp.ForceFactoryBeanFactoryPostProcessor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMerge {
	private static final Logger log = LoggerFactory.getLogger(TestMerge.class);
	/**
	 * 1. addBeanFactoryPostProcessor注册的bdrpp执行前不需要
	 * 合并,因为bdrpp只能注册和修改已经有的BD
	 * 2. 执行BeanFactoryPostProcessor执行会进行一次BD的合并
	 * 因为api注册的bdrpp可能添加修改BD,每次执行bfpp之前都需要
	 * 去获取一次但不会重新合并已经合并的,会合并新加的
	 *
	 * 3. 执行完bfpp默认会将所有已经合并的BD标记为需要
	 * 重新合并,因为此时已经不涉及修改BD的操作,已经修改的
	 * 需要生效
	 *
	 *
	 *
	 *
	 */
	@Test
	public void testMergeBD() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.kanozz.beanfactory.beandefinition.merge");
		context.addBeanFactoryPostProcessor(ForceFactoryBeanFactoryPostProcessor.INSTANCE);
		// 初次会创建,因为还是单例
		context.refresh();
		// 每次获取都会重新创建一个
//		Object beforeKanoA = context.getBean("kanoA");
//		Object afterKanoA = context.getBean("kanoA");
//		log.info("same kanoA = {}",beforeKanoA == afterKanoA);
	}


}
