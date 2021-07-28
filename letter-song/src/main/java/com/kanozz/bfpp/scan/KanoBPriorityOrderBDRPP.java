package com.kanozz.bfpp.scan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.PriorityOrdered;

@Slf4j
class KanoBPriorityOrderBDRPP implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {

	KanoBPriorityOrderBDRPP(){
		log.info("KanoBPriorityOrderBDRPP create");
	}


	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

	}

	@Override
	public int getOrder() {
		return 0;
	}
}
