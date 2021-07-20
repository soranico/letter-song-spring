package com.kanozz.beanfactory.scanner.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class KanoA implements BeanNameAware {

	@Override
	public void setBeanName(String name) {
		log.info("KanoA = {}",name);
	}
}
