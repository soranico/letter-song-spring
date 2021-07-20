package com.kanozz.beanfactory.scanner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.kanozz.beanfactory.scanner.ann")
@Slf4j
class KanoConfig implements BeanNameAware {
	@Override
	public void setBeanName(String name) {
		log.info("KanoConfig = {}",name);
	}
}
