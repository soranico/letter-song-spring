package com.kanozz.bfpp.scan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class KanoAPriorityOrderBDRPP implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {

	KanoAPriorityOrderBDRPP(){
		log.info("KanoAPriorityOrderBDRPP create");
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
