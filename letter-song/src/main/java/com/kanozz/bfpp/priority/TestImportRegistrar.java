package com.kanozz.bfpp.priority;

import org.junit.Test;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestImportRegistrar {

	/**
	 *
	 * 当将BFPP注册为BD,并且需要使用一些内部bean
	 * 比如ApplicationContext时
	 * 此时因为处理注解的BPP
	 * @see org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor
	 * @see org.springframework.context.annotation.CommonAnnotationBeanPostProcessor
	 * 还没有创建为bean 自然不在
	 * @see org.springframework.beans.factory.support.AbstractBeanFactory#beanPostProcessors
	 *
	 * 因此必须使用接口来设置这些值
	 * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#initializeBean(String, Object, RootBeanDefinition)
	 *
	 * @see org.springframework.context.support.ApplicationContextAwareProcessor#postProcessBeforeInitialization(Object, String)
	 * 处理Aware回调接口
	 *
	 * 这也是先执行工厂预处理的原因,因为需要先去注册一些基本的处理BFPP
	 * @see org.springframework.context.support.AbstractApplicationContext#prepareBeanFactory(ConfigurableListableBeanFactory)
	 *
	 */
	@Test
	public void testRegImportRegistrar(){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		AbstractBeanDefinition importRegistrarBD = BeanDefinitionBuilder
				.rootBeanDefinition(KanoAppContextOrderedBfpp.class).getBeanDefinition();
		context.registerBeanDefinition("dubbo",importRegistrarBD);
		context.refresh();
		KanoAppContextOrderedBfpp contextBean = context.getBean(KanoAppContextOrderedBfpp.class);
		contextBean.kano();
	}
}
