package com.kanozz.cycle;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;

public class TestCycle {
	private static final Logger log = LoggerFactory.getLogger(TestCycle.class);


	@Test
	public void testCycleWithFiled(){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(KanoA.class,KanoB.class,KanoAspect.class);
		context.register(AnnotationAwareAspectJAutoProxyCreator.class);
		context.refresh();
	}

	/**
	 *
	 * 构造方法循环依赖
	 *
	 * bean和对象不同,对象通过BPP作用后转为bean
	 * 对象 - 初始化(属性填充,回调方法) - bean - 初始化完成(修改bean的机会,比如代理)
	 * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsBeforeInstantiation(Class, String)
	 *
	 * 对象是 bean 的来源,如果在构造方法循环依赖的话,那么久无法得到对象,因此需要对一个对象进行
	 * 懒创建以破解无法创建对象的问题
	 *
	 * @see ContextAnnotationAutowireCandidateResolver#buildLazyResolutionProxy(org.springframework.beans.factory.config.DependencyDescriptor, java.lang.String)
	 *
	 */
	@Test
	public void testCycleWithConstruct(){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(KanoC.class,KanoD.class);
		context.refresh();
		KanoD kanoD = context.getBean(KanoD.class);
		log.info("kanoC = {}",kanoD.getKanoC());
	}
}
