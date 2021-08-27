package com.kanozz.listener;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.event.ApplicationEventMulticaster;


class KanoApplicationListenerBpp implements BeanPostProcessor, ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof KanoListenerImpl){
			ApplicationEventMulticaster multicaster = applicationContext.getBean(ApplicationEventMulticaster.class);
			multicaster.addApplicationListener((KanoListenerImpl)bean);
		}
		return bean;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
