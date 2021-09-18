package com.kanozz.beandefinition.callbackmethod;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Field;

@Slf4j
public class TestCallBack {
	private static final Logger log = LoggerFactory.getLogger(TestCallBack.class);
	/**
     *
	 * 继承接口的不需要存储回调方法
	 * 因为对于接口而言可以直接类型转换调用即可
	 *
	 */
	@Test
	public void testInterface() throws Exception{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(KanoA.class);
		context.refresh();
		DefaultListableBeanFactory factory = (DefaultListableBeanFactory) context.getBeanFactory();
		RootBeanDefinition kanoA = (RootBeanDefinition) factory.getMergedBeanDefinition("kanoA");
		log.info("init method  = {}",kanoA.getInitMethodName());
		Field field = RootBeanDefinition.class.getDeclaredField("externallyManagedInitMethods");
		field.setAccessible(true);
		log.info("externallyManagedInitMethods = {}",field.get(kanoA));
	}

	/**
	 * 手动添加的会存放在里面
	 * @see org.springframework.beans.factory.config.BeanDefinition#setInitMethodName(String)
	 *
	 */
	@Test
	public void testManual() throws Exception{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		AbstractBeanDefinition kano = BeanDefinitionBuilder.genericBeanDefinition(KanoB.class)
				.setInitMethodName("manualInit").getBeanDefinition();
		context.registerBeanDefinition("kanoB",kano);
		context.refresh();
		DefaultListableBeanFactory factory = (DefaultListableBeanFactory) context.getBeanFactory();
		RootBeanDefinition kanoB = (RootBeanDefinition) factory.getMergedBeanDefinition("kanoB");
//		log.info("init method  = {}",kanoB.getInitMethodName());
		Field field = RootBeanDefinition.class.getDeclaredField("externallyManagedInitMethods");
		field.setAccessible(true);
//		log.info("externallyManagedInitMethods = {}",field.get(kanoB));
	}

	/**
	 * 注解的回调方法,会存放在
	 * @see RootBeanDefinition#externallyManagedInitMethods
	 * 这也是为什么在创建bean之前需要合并为 RootBeanDefinition 的一个原因
	 */
	@Test
	public void testAnnotation() throws Exception{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		AbstractBeanDefinition kano = BeanDefinitionBuilder.genericBeanDefinition(KanoC.class)
				.setInitMethodName("manualInit").getBeanDefinition();
		kano.setInitMethodName("manualInit");
		context.registerBeanDefinition("kanoC",kano);
		context.refresh();
		DefaultListableBeanFactory factory = (DefaultListableBeanFactory) context.getBeanFactory();
		RootBeanDefinition kanoC = (RootBeanDefinition) factory.getMergedBeanDefinition("kanoC");
//		log.info("init method  = {}",kanoC.getInitMethodName());
		Field field = RootBeanDefinition.class.getDeclaredField("externallyManagedInitMethods");
		field.setAccessible(true);
//		log.info("externallyManagedInitMethods = {}",field.get(kanoC));
	}


	/**
	 *
	 *
	 *
	 */
	@Test
	public void testCheckCallBack() throws Exception{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		AbstractBeanDefinition kano = BeanDefinitionBuilder.genericBeanDefinition(KanoC.class)
				.setInitMethodName("manualInit").getBeanDefinition();
		kano.setInitMethodName("manualInit");
		context.registerBeanDefinition("kanoC",kano);
		context.refresh();
		DefaultListableBeanFactory factory = (DefaultListableBeanFactory) context.getBeanFactory();
		RootBeanDefinition kanoC = (RootBeanDefinition) factory.getMergedBeanDefinition("kanoC");
//		log.info("init method  = {}",kanoC.getInitMethodName());
		Field field = RootBeanDefinition.class.getDeclaredField("externallyManagedInitMethods");
		field.setAccessible(true);
//		log.info("externallyManagedInitMethods = {}",field.get(kanoC));
	}

	/**
	 * 通过配置类 @Bean指定的回调方法
	 * 存放在 initMethodName 里面
	 */
	@Test
	public void testBeanCallBack() throws Exception{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(KanoConfig.class,KanoB.class);
		context.refresh();
		DefaultListableBeanFactory factory = (DefaultListableBeanFactory) context.getBeanFactory();
		RootBeanDefinition kanoA = (RootBeanDefinition) factory.getMergedBeanDefinition("kanoA");
//		log.info("init method  = {}",kanoA.getInitMethodName());
		Field field = RootBeanDefinition.class.getDeclaredField("externallyManagedInitMethods");
		field.setAccessible(true);
//		log.info("externallyManagedInitMethods = {}",field.get(kanoA));
	}


}
