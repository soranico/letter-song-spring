package com.kanozz.beanfactory.scanner.ann;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
class KanoC implements BeanNameAware {
	private static final Logger log = LoggerFactory.getLogger(KanoC.class);
	@Override
	public void setBeanName(String name) {
		log.info("KanoC = {}",name);
	}
}
