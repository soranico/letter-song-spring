package com.kanozz.bfpp.priority;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

class KanoAppContextOrderedBfpp implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {
	private static final Logger log = LoggerFactory.getLogger(KanoAppContextOrderedBfpp.class);
//	@Autowired
	private ApplicationContext applicationContext;

	private BeanDefinitionRegistry registry;

	public void kano(){
//		log.info("applicationContext = {}",applicationContext);
	}


	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//			log.info("dubbo registry bpp");
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		this.registry = registry;
	}


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
