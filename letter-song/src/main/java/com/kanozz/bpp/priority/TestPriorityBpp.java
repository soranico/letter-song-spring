package com.kanozz.bpp.priority;

import org.junit.Test;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestPriorityBpp {

	/**
	 * 一个BPP需要生效必须存在于 beanPostProcessors集合 中
	 * {@link org.springframework.beans.factory.support.AbstractBeanFactory#beanPostProcessors}
	 *
	 * 如果添加了一个BPP并实现了Ordered(需要比较优先级)或者 PriorityOrdered接口
	 * 在aop的情况下如果注入一个需要被aop拦截的bean会导致aop失效
	 *
	 * 因为在初始化BPP的时候,会首先初始化实现优先级接口的,然后放入集合
	 * AnnotationAwareAspectJAutoProxyCreator 虽然间接实现了 Ordered接口
	 * @see org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator
	 * 但优先级默认最低
	 *
	 * 即便aop先创建为bean,但处理是一批放入也是没有办法生效的
	 * 通过Import方式注册进去可以让 aop的先实例化
	 * 通过注解的因为扫描在前所有没有办法让aop先实例化
	 *
	 *
	 */
	@Test
	public void testAspectNotEffect(){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(KanoA.class,KanoAspect.class, KanoAutoOrderedBpp.class);
		context.refresh();
		// 此时 kanoA 不会被 aop代理
//		context.getBean(KanoA.class).kano();
	}

	/**
	 *
	 * 如果一个类实现了BPP并且实现了PriorityOrdered接口
	 * 那么注解标记显示自动注入或者自动注入将不会生效
	 * 因为处理属性注入的是
	 * @see org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor
	 * @see org.springframework.context.annotation.CommonAnnotationBeanPostProcessor
	 * 这两个BPP,对于spring而言会将同一个类型的一起实例化然后添加到 beanPostProcessors 中生效
	 *
	 *
	 * 这个也是为什么需要通过接口实现获取 ApplicationContext 这些属性的原因
	 * 因为处理的是直接new 放进去的
	 * @see org.springframework.context.support.AbstractApplicationContext#prepareBeanFactory(ConfigurableListableBeanFactory) 
	 *
	 */
	@Test
	public void testNotEffectAutowired(){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(KanoAppContextOrderedBpp.class)
				.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE).getBeanDefinition();
		context.registerBeanDefinition("kanoAppContextOrderedBpp",beanDefinition);
		context.register(KanoA.class);
		context.refresh();
		context.getBean(KanoAppContextOrderedBpp.class).kano();
		// 此时 kanoA 不会被 aop代理
//		context.getBean(KanoA.class).kano();
	}




}
