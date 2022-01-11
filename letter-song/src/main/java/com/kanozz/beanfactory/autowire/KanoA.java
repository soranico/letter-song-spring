package com.kanozz.beanfactory.autowire;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
class KanoA implements BeanNameAware {
	private static final Logger log = LoggerFactory.getLogger(KanoA.class);

	@Autowired
	private Stream<KanoB> kanoBStream;

	@Override
	public void setBeanName(String name) {
		log.info("KanoA = {}",name);
	}
}
