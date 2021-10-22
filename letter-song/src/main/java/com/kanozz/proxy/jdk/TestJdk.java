package com.kanozz.proxy.jdk;

import org.junit.Test;
import org.springframework.aop.TargetSource;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestJdk {

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
	 *
	 */
	@Test
	public void testJdkProxy() throws Exception{
//		outCglib();
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(KanoAspect.class, KanoJdkImpl.class);
		context.register(AnnotationAwareAspectJAutoProxyCreator.class);
		context.refresh();
//		Object kanoJdkImpl = context.getBean("kanoJdkImpl");
		// 获取目标对象
//		TargetSource targetSource = ((Advised) kanoJdkImpl).getTargetSource();
//		Object target = targetSource.getTarget();
		context.getBean(KanoJdk.class).kano01();
	}



}
