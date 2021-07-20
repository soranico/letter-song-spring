package com.kanozz.beanfactory.clear.metacache.bfpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;

public class ForceFactoryBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	public static ForceFactoryBeanFactoryPostProcessor INSTANCE = new ForceFactoryBeanFactoryPostProcessor();

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		ScannedGenericBeanDefinition kanoA = (ScannedGenericBeanDefinition) beanFactory.getBeanDefinition("kanoA");
		beanFactory.freezeConfiguration();
		kanoA.setScope("prototype");
	}
}
