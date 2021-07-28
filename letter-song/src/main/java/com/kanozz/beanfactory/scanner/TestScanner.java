package com.kanozz.beanfactory.scanner;


import org.junit.Test;
import org.mybatis.spring.mapper.ClassPathMapperScanner;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

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

	/**
	 *
	 * @see org.mybatis.spring.annotation.MapperScan
	 * 导入了一个实现 ImportBeanDefinitionRegistrar 的
	 * MapperSaccnerRegistrar 重写registerBeanDefinitions
	 * 注册了一个BD(MapperScannerConfigurer)
	 * @see org.mybatis.spring.annotation.MapperScannerRegistrar#registerBeanDefinitions(AnnotationMetadata, BeanDefinitionRegistry)
	 * 同时添加一些生成这个BD对应bean所需要的属性(用于扫描器)，如果存在的话(如果修改自动注入模型的话需要设置值)
	 *
	 * MapperScannerConfigurer 实现 BeanDefinitionRegistryPostProcessor
	 * 并重写postProcessorBeanDefinitionRegistry方法用于注册BD
	 * @see org.mybatis.spring.mapper.MapperScannerConfigurer#postProcessBeanDefinitionRegistry(BeanDefinitionRegistry)
	 *
	 * ClassPathMapperScanner 继承 ClassPathBeanDefinitionScanner 创建时默认不使用父类的过滤器（即Spring的）
	 * 通过过滤器来扫描特定的类（默认扫描指定包下所有接口,如果@MapperScan指定注解或接口则只扫描符合的）
	 * @see ClassPathMapperScanner#registerFilters()
	 * 只要是接口就满足
	 * @see ClassPathMapperScanner#isCandidateComponent(org.springframework.beans.factory.annotation.AnnotatedBeanDefinition)
	 *
	 * 扫描出来的BD会设置beanClass为MapperFactoryBean(用于生成真实的代理bean),如果没有指定使用的
	 * SqlSessionFactory 或者 SqlSessionTemplate 那么会修改BD的自动注入模型为AUTO_BY_TYPE
	 *
	 *
	 * 注意
	 *
	 * spring在扫描指定包下面的class时使用的是asm技术,防止破坏用户行为
	 * 因为有些class需要在特定时机由用户自己加载，而非扫描时被动加载
	 * 其中使用 MetadataReader 来封装一个扫描出来的class文件
	 * @see org.springframework.core.type.classreading.SimpleMetadataReader
	 * 而其中的 AnnotationMeata 属性则封装了注解相关的信息(哪个注解,哪个类上等)
	 * @see org.springframework.core.type.classreading.SimpleAnnotationMetadata
	 * 其中 SimpleAnnotationMetadata 的 MergedAnnotations 封装了所有注解信息
	 * @see org.springframework.core.annotation.TypeMappedAnnotations
	 *
	 */
	@Test
	public void testMyBatisScanner(){

		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();

		context.register(KanoMybatisConfig.class);

		context.refresh();

		Object kanoMapper = context.getBean("kanoMapper");


	}
}
