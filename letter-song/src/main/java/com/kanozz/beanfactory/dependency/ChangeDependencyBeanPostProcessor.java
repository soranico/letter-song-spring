package com.kanozz.beanfactory.dependency;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;

/**
 * 修改注入模型
 */
public class ChangeDependencyBeanPostProcessor implements BeanFactoryPostProcessor {

	public static final ChangeDependencyBeanPostProcessor INSTANCE = new ChangeDependencyBeanPostProcessor();
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		/**
		 * 指定注入属性Kano的值
		 */
		beanFactory.registerResolvableDependency(Kano.class, KanoImplC.INSTANCE);
		ScannedGenericBeanDefinition kanoB = (ScannedGenericBeanDefinition) beanFactory.getBeanDefinition("kanoB");
//		kanoB.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME);
		kanoB.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);

	}
}
