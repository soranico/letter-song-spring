package com.kanozz.lazy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@EnableTransactionManagement
class LazyKanoB {
	private static final Logger log = LoggerFactory.getLogger(LazyKanoB.class);
	public String lazy(){
//		log.info("there have proxy");
		return "lazy";
	}
}
