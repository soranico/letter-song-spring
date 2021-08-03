package com.kanozz.configclass.configuration;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.type.classreading.MetadataReaderFactory;

public class TestConfiguration {


	/**
	 *
	 * 对于 @Configuration 且没有显示标记不需要代理 的 BD而言
	 * 会被标记为 full 表示需要代理
	 *
	 */
	@Test
	public void testConfiguration() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 手动注册一个BD,类型不能为 AnnotationBeanDefinition
		context.register(KanoConfiguration.class);
		context.refresh();
	}



	/**
	 *
	 * 对于一个不是 AnnotationBeanDefinition 的 BD而言
	 * 就算其被注解标记 为配置类 ,那么也不会被当成配置类去解析
	 * @see org.springframework.context.annotation.ConfigurationClassUtils#checkConfigurationClassCandidate(BeanDefinition, MetadataReaderFactory) 
	 *
	 *
	 */
	@Test
	public void testConfigurationWithExclude() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 手动注册一个BD,类型不能为 AnnotationBeanDefinition
		context.registerBeanDefinition("kanoConfigurationWithExclude", new RootBeanDefinition(KanoConfigurationWithExclude.class));
		context.refresh();
	}


	/**
	 * 
	 * 配置类会被封装为 ConfigurationClass 其中所有@Bean方法会在解析
	 * 阶段放入
	 * @see org.springframework.context.annotation.ConfigurationClass#beanMethods
	 * 属性中已用于后续处理变成 BD
	 * 
	 */
	@Test
	public void testConfigurationWithBean(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(KanoConfigurationWithBean.class);
		context.refresh();
	}


	/**
	 * 
	 * 对于存在于工厂中的 BD而言,只要其存在 @Bean标记的方法 那么会
	 * 被当成配置类处理
	 * @see org.springframework.context.annotation.ConfigurationClassUtils#checkConfigurationClassCandidate(BeanDefinition, MetadataReaderFactory) 
	 * 
	 * 
	 */
	@Test
	public void testBean() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 手动注册一个BD,类型不能为 AnnotationBeanDefinition
		context.registerBeanDefinition("kanoWithBean", new RootBeanDefinition(KanoWithBean.class));
		context.refresh();
	}












}
