package com.kanosd.beanfactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;

/**
 * 修改注入模型
 */
public class ChangeAutowireModelBeanPostProcessor implements BeanFactoryPostProcessor {

	public static final ChangeAutowireModelBeanPostProcessor INSTANCE = new ChangeAutowireModelBeanPostProcessor();
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		/**
		 * 忽略KanoB类型的注入
		 */
		beanFactory.ignoreDependencyInterface(KanoB.class);
		/**
		 * 修改注入模型为by_type
		 * 默认扫描
		 */
		AnnotatedGenericBeanDefinition kanoA = (AnnotatedGenericBeanDefinition) beanFactory.getBeanDefinition("kanoA");
		kanoA.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME);

	}
}
