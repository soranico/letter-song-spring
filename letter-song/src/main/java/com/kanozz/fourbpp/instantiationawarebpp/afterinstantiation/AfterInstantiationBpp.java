package com.kanozz.fourbpp.instantiationawarebpp.afterinstantiation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

class AfterInstantiationBpp implements InstantiationAwareBeanPostProcessor {

	public static final AfterInstantiationBpp INSTANCE = new AfterInstantiationBpp();

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		if (bean instanceof AfterBppA || bean instanceof AfterBppC){
			return false;
		}
		return true;

	}
}
