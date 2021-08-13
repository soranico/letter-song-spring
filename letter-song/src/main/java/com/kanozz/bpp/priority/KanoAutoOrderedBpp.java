package com.kanozz.bpp.priority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

import javax.annotation.PostConstruct;

class KanoAutoOrderedBpp implements BeanPostProcessor, Ordered {

	@Autowired
	private KanoA kanoA;

	@PostConstruct
	public void init(){
		kanoA.kano();
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}
}
