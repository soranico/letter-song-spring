package com.kanozz.beanfactory.scanner.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
class KanoA implements BeanNameAware {
	private static final Logger log = LoggerFactory.getLogger(KanoA.class);
	@Override
	public void setBeanName(String name) {
		log.info("KanoA = {}",name);
	}
}
