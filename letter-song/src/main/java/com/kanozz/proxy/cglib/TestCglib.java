package com.kanozz.proxy.cglib;

import org.junit.Test;
import org.springframework.aop.TargetSource;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Method;

public class TestCglib {

	private static final String CGLIB_CLASS_DIR = "/Users/kano/Desktop/mycode/study/spring-framework-5.3.7/letter-song/src/main/java/com/kanozz/proxy/cglib/source";
	public void outCglib() {
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, CGLIB_CLASS_DIR);
	}

	/**
	 * 代理类里面在调用方法时会调用
	 * this.CGLIB$CALLBACK_0.intercept()
	 * 而这个对象的值是 Callback[] callbacks 
	 * this.CGLIB$CALLBACK_0 = (MethodInterceptor)callbacks[0];
	 * 这个值是在生成代理前默认添加的
	 * @see org.springframework.aop.framework.CglibAopProxy#getCallbacks(Class)
	 * 第一个为 DynamicAdvisedInterceptor
	 * @see org.springframework.aop.framework.CglibAopProxy.DynamicAdvisedInterceptor
	 *
	 * 生成代理是在BPP的 初始化完成方法中
	 * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsAfterInitialization(Object, String)
	 *
	 * 具体处理的是 AnnotationAwareAspectJAutoProxyCreator 的父类 AbstractAutoProxyCreator 中生成的
	 * @see org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#postProcessAfterInitialization(Object, String) 
	 * 
	 * 1. 获取有效的拦截链
	 * @see org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator#getAdvicesAndAdvisorsForBean(Class, String, TargetSource) 
	 *
	 * 2. 进行代理
	 * @see org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#createProxy(Class, String, Object[], TargetSource)
	 * 最终调用
	 * @see org.springframework.aop.framework.ProxyFactory#getProxy(ClassLoader)
	 *
	 * 2.1 获取生成当前代理类的方式
	 * @see org.springframework.aop.framework.DefaultAopProxyFactory#createAopProxy(AdvisedSupport)
	 *
	 * 3. 生成代理
	 * @see org.springframework.aop.framework.AopProxy#getProxy(ClassLoader)
	 *
	 * 3.1 cglib代理
	 * @see org.springframework.aop.framework.ObjenesisCglibAopProxy#getProxy(ClassLoader)
	 * 生成的代理类的处理用户方法的Callback为
	 * @see org.springframework.aop.framework.CglibAopProxy.DynamicAdvisedInterceptor
	 *
	 * 在生成代理类的每个方法的时候会调用判断处理每个方法的拦截器
	 * @see org.springframework.aop.framework.CglibAopProxy.ProxyCallbackFilter#accept(Method)
	 * 
	 * 
	 * 
	 * 3.2 jdk代理
	 * @see org.springframework.aop.framework.JdkDynamicAopProxy
	 * 生成代理对象传入的 InvocationHandler 就是 JdkDynamicAopProxy
	 * @see org.springframework.aop.framework.JdkDynamicAopProxy#getProxy(ClassLoader)
	 *
	 */
	@Test
	public void testCglibProxy(){
//		outCglib();
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(KanoAspect.class,KanoCglib.class);
		context.register(AnnotationAwareAspectJAutoProxyCreator.class);
		context.refresh();
		context.getBean(KanoCglib.class).kano01();
	}

	/**
	 * ProceedingJoinPoint is only supported for around advice
	 */
	@Test
	public void testPrototypeCglibProxy(){
//		outCglib();
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(KanoAspect.class);
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(KanoPrototypeCglib.class).setScope(AbstractBeanFactory.SCOPE_PROTOTYPE)
				.getBeanDefinition();
		context.registerBeanDefinition("kanoPrototypeCglib",beanDefinition);
		context.register(AnnotationAwareAspectJAutoProxyCreator.class);
		context.refresh();
		context.getBean(KanoPrototypeCglib.class).kano01();
	}


	@Test
	public void testCglibProxyBefore(){
//		outCglib();
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(KanoAspect.class,KanoCglib.class);
		context.register(AnnotationAwareAspectJAutoProxyCreator.class);
		context.refresh();
		context.getBean(KanoCglib.class).before();
	}
}
