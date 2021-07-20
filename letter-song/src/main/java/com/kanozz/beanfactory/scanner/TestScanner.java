package com.kanozz.beanfactory.scanner;


import org.junit.Test;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.core.annotation.AnnotationAttributes;

public class TestScanner {

	/**
	 *
	 * @see AnnotationConfigApplicationContext#setBeanNameGenerator(org.springframework.beans.factory.support.BeanNameGenerator)
	 * 这个beanName生成策略是用于scan扫描和register注册的
	 * @see org.springframework.context.annotation.AnnotationConfigRegistry#scan(String...)
	 * @see org.springframework.context.annotation.AnnotationConfigRegistry#register(Class[])
	 *
	 *
	 * 默认会将这个beanName注册为单例,在通过扫描的ComponentSacnAnnotationParser
	 * 处理扫描时会新建一个ClassPathBeanDefinitionScanner,如果没指定beanName生成器
	 * 则会使用单例池中的(如果存在)
	 * @see org.springframework.context.annotation.ConfigurationClassPostProcessor#processConfigBeanDefinitions(BeanDefinitionRegistry) 
	 * @see org.springframework.context.annotation.ComponentScanAnnotationParser#parse(AnnotationAttributes, String)
	 *
	 *
	 *
	 *
	 *
	 *
	 */
	@Test
	public void testScanner(){
		AnnotationConfigApplicationContext context = new
				AnnotationConfigApplicationContext();
		context.setBeanNameGenerator(KanoBeanNameGenerator.INSTANCE);
		// 移除单例池中的BeanNameGenerator，让api和注解扫描使用不同的
		((DefaultListableBeanFactory)context.getBeanFactory()).destroySingleton(AnnotationConfigUtils.CONFIGURATION_BEAN_NAME_GENERATOR);
		context.register(KanoConfig.class);
		context.scan("com.kanozz.beanfactory.scanner.bean");
		context.refresh();
	}

	@Test
	public void testMyBatisScanner(){

	}
}
