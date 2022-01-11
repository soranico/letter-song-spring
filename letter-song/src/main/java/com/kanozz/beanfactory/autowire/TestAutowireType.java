package com.kanozz.beanfactory.autowire;


import org.junit.Test;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.annotation.AnnotationAttributes;

public class TestAutowireType {

	/**
	 * @see AnnotationConfigApplicationContext#setBeanNameGenerator(org.springframework.beans.factory.support.BeanNameGenerator)
	 * 这个beanName生成策略是用于scan扫描和register注册的
	 * @see org.springframework.context.annotation.AnnotationConfigRegistry#scan(String...)
	 * @see org.springframework.context.annotation.AnnotationConfigRegistry#register(Class[])
	 * <p>
	 * <p>
	 * 默认会将这个beanName注册为单例,在通过扫描的ComponentSacnAnnotationParser
	 * 处理扫描时会新建一个ClassPathBeanDefinitionScanner,如果没指定beanName生成器
	 * 则会使用单例池中的(如果存在)
	 * @see org.springframework.context.annotation.ConfigurationClassPostProcessor#processConfigBeanDefinitions(BeanDefinitionRegistry)
	 * @see org.springframework.context.annotation.ComponentScanAnnotationParser#parse(AnnotationAttributes, String)
	 */
	@Test
	public void testScanner() {
		AnnotationConfigApplicationContext context = new
				AnnotationConfigApplicationContext();
		context.register(KanoA.class,KanoB.class);
		context.refresh();
	}
}
