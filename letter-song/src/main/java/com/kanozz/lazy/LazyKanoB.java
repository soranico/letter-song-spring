package com.kanozz.lazy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Slf4j
@EnableTransactionManagement
class LazyKanoB {

	public String lazy(){
		log.info("there have proxy");
		return "lazy";
	}
}
