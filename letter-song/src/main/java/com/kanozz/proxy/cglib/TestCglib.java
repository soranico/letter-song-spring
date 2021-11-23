package com.kanozz.proxy.cglib;

import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.TargetSource;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.beans.factory.support.*;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.Method;

public class TestCglib {

	private static final String CGLIB_CLASS_DIR = "/Users/kano/Desktop/mycode/study/spring-framework-5.3.7/letter-song/src/main/java/com/kanozz/proxy/cglib/source";
	public void outCglib() {
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, CGLIB_CLASS_DIR);
	}

	/**
	 *
	 * @see org.springframework.context.annotation.EnableAspectJAutoProxy
	 * @see org.springframework.context.annotation.AspectJAutoProxyRegistrar#registerBeanDefinitions(AnnotationMetadata, BeanDefinitionRegistry)
	 *
	 *
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
	 *
	 * 对于注解切面而言 最终都会调用到 AbstractAspectJAdvice
	 * @see org.springframework.aop.aspectj.AbstractAspectJAdvice#invokeAdviceMethodWithGivenArgs(Object[])
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

	/**
	 * 执行方法首先执行这个
	 * @see org.springframework.aop.framework.CglibAopProxy.DynamicAdvisedInterceptor#intercept(Object, Method, Object[], MethodProxy)
	 *
	 * 如果存在适配的拦截器会创建一个 CglibMethodInvocation 对象
	 * @see org.springframework.aop.framework.CglibAopProxy.CglibMethodInvocation#proceed()
	 * 最终调用其父类
	 * @see ReflectiveMethodInvocation#proceed()
	 *
	 * 一般第一个调用的是 ExposeInvocationIntercepotor 的 invoke(this = ReflectiveMethodInvocation)
	 * @see org.springframework.aop.interceptor.ExposeInvocationInterceptor#invoke(MethodInvocation)
	 * 保存当前的 ReflectiveMethodInvocation 对象到 ThreadLocal 中
	 *
	 * 对于 @Before
	 * 调用的是  MethodBeforeAdviceInterceptor
	 * @see org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor#invoke(MethodInvocation) 
	 *
	 */
	@Test
	public void testCglibProxyBefore(){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(KanoAspect.class,KanoCglib.class);
		context.register(AnnotationAwareAspectJAutoProxyCreator.class);
		context.refresh();
		context.getBean(KanoCglib.class).before();
	}

	/**
	 * @see org.springframework.aop.aspectj.AspectJAroundAdvice#invoke(MethodInvocation) 
	 */
	@Test
	public void testCglibProxyAround(){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(KanoAspect.class,KanoCglib.class);
		context.register(AnnotationAwareAspectJAutoProxyCreator.class);
		context.refresh();
		context.getBean(KanoCglib.class).around();
	}

	/**
	 * @see org.springframework.aop.aspectj.AspectJAfterAdvice#invoke(MethodInvocation)
	 */
	@Test
	public void testCglibProxyAfter(){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(KanoAspect.class,KanoCglib.class);
		context.register(AnnotationAwareAspectJAutoProxyCreator.class);
		context.refresh();
		context.getBean(KanoCglib.class).after();
	}

	/**
	 * @see org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor#invoke(MethodInvocation)
	 */
	@Test
	public void testCglibProxyAfterReturning(){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(KanoAspect.class,KanoCglib.class);
		context.register(AnnotationAwareAspectJAutoProxyCreator.class);
		context.refresh();
		context.getBean(KanoCglib.class).afterReturning();
	}

	/**
	 * @see org.springframework.aop.aspectj.AspectJAfterThrowingAdvice#invoke(MethodInvocation)
	 */
	@Test
	public void testCglibProxyAfterThrowing(){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(KanoAspect.class,KanoCglib.class);
		context.register(AnnotationAwareAspectJAutoProxyCreator.class);
		context.refresh();
		context.getBean(KanoCglib.class).throwing();
	}
}
