package com.kanozz.transaction;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Method;

public class TestTransaction {
	private static final Logger log = LoggerFactory.getLogger(TestTransaction.class);
	private static final String CGLIB_CLASS_DIR = "/Users/kano/Desktop/mycode/study/spring-framework-5.3.7/letter-song/src/main/java/com/kanosd/transaction/cglib";

	public void outJdk() {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
	}

	public void outCglib() {
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, CGLIB_CLASS_DIR);
	}

	/**
	 * 非事务方法A() 里调用事务方法 B()
	 * 假设 实例 kano 其生成的代理实例为 kanoProxy
	 *
	 * 调用 A() 时 通过
	 * kanoProxy.A() 此时会调用到 DynamicAdvisedInterceptor 的 intercept()
	 * 此时获取到的拦截器为 空 直接进行方法调用
	 * 此时调用的是 kano.A() 也就是执行的是非代理实例的方法
	 * 在A()中 直接调用 B() 对于 B()而言隐含了 this.B()
	 * 而当前的this 为 kano 而不是代理实例 kanoProxy 因此事务不会生效
	 *
	 *
	 * 1. 首先调用到 DynamicAdvisedInceptor 的 intercept()
	 * 这一步是必须的
	 * @see org.springframework.aop.framework.CglibAopProxy.DynamicAdvisedInterceptor#intercept(Object, Method, Object[], MethodProxy)
	 *
	 * 2. 获取对当前方法生效的 切面
	 * @see org.springframework.aop.framework.AdvisedSupport#getInterceptorsAndDynamicInterceptionAdvice(Method, Class) 
	 *
	 *
	 *
	 */
	@Test
	public void testTransactionCgLib() {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
//		context.register(AnnotationAwareAspectJAutoProxyCreator.class);
		context.register(KanoConfig.class);
//		context.register(AutoProxyRegistrar.class, ProxyTransactionManagementConfiguration.class);
		context.register(TransactionKanoA.class, TransactionKanoB.class);
		context.refresh();
		TransactionKanoA transactionKanoA = context.getBean(TransactionKanoA.class);
		transactionKanoA.testTransactionAA();
//		transactionKanoA.testTransactionAB();

	}


}
