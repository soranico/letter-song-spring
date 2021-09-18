package com.kanozz.bpp.priority;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

@Slf4j
class KanoAppContextOrderedBpp implements BeanPostProcessor, PriorityOrdered {

	@Autowired
	private ApplicationContext applicationContext;

	private KanoA kanoA;

	public void setKanoA(KanoA kanoA) {
		this.kanoA = kanoA;
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

	public void kano(){
//		log.info("applicationContext = {}",applicationContext);
//		log.info("kanoA = {}",kanoA);
	}
}
