package com.kanozz.bfpp.scan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class KanoAOrderBDRPP implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {

	KanoAOrderBDRPP(){
		log.info("KanoAPriorityOrderBDRPP create");
	}


	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		registry.registerBeanDefinition("kanoBPriorityOrderBDRPP",new RootBeanDefinition(KanoBPriorityOrderBDRPP.class));
	}

	@Override
	public int getOrder() {
		return 0;
	}
}
