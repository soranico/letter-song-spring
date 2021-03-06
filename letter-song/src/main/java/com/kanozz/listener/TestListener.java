package com.kanozz.listener;

import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AutoProxyRegistrar;
import org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration;

public class TestListener {


	/**
	 *
	 * 比如在BPP中获取到 事件发布者集合(用于发布事件给观察者)
	 * @see org.springframework.context.event.ApplicationEventMulticaster
	 *
	 * 默认实现里面的defaultRetriever(一个内部类)存放了所有观察者
	 * @see org.springframework.context.event.AbstractApplicationEventMulticaster
	 *
	 * 在添加一个观察者时,需要获取这个观察者的目标对象
	 * 因为可以通过后置处理器在bean被代理之前就注册到监听集合中
	 * 而后续通过正常的生命周期代理后添加到 观察者集合中时,如果没有
	 * 不移除之前注册的目标对象的话就会出现一个观察者被通知两次
	 *
	 * @see org.springframework.context.event.AbstractApplicationEventMulticaster#addApplicationListener(ApplicationListener)
	 *
	 *
	 */
	@Test
	public void testProxySameListener(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		/**
		 * 正在执行初始化之前手动添加
		 * @see KanoApplicationListenerBpp#postProcessBeforeInitialization(Object, String)
		 * 而代理是在属性填充后
		 */
		context.register(KanoApplicationListenerBpp.class, KanoListenerImpl.class,KanoAspect.class);
		// aop支持
		context.register(AnnotationAwareAspectJAutoProxyCreator.class);
		context.refresh();
	}


	/***
	 *
	 * 注册一个 EventListenerMethodProcessor 负责处理 @EventListener
	 * @see org.springframework.context.annotation.AnnotationConfigUtils#registerAnnotationConfigProcessors(BeanDefinitionRegistry, Object)
	 * @see org.springframework.context.event.EventListenerMethodProcessor
	 *
	 * 实现了 SmartInitializingSingleton 接口会在 所有单例bean创建完成后回调
	 *
	 *
	 *
	 */
	@Test
	public void testAnnotationListener(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register( KanoListenerAnnotation.class,KanoAspect.class);
		context.register(AutoProxyRegistrar.class, ProxyTransactionManagementConfiguration.class);
		context.register(AnnotationAwareAspectJAutoProxyCreator.class);
		context.refresh();
	}




















}
