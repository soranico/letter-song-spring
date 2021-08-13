package com.kanozz.listener;

import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
		context.register(KanoApplicationListenerBpp.class,KanoListener.class,KanoAspect.class);
		// aop支持
		context.register(AnnotationAwareAspectJAutoProxyCreator.class);
		context.refresh();
	}


}
