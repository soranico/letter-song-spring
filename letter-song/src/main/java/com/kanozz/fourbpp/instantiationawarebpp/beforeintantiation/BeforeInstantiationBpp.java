package com.kanozz.fourbpp.instantiationawarebpp.beforeintantiation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

class BeforeInstantiationBpp implements InstantiationAwareBeanPostProcessor {

	public static final BeforeInstantiationBpp INSTANCE = new BeforeInstantiationBpp();
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if (beanClass == BeforeBppA.class){
			return new BeforeBppA();
		}
		return null;
	}

}
