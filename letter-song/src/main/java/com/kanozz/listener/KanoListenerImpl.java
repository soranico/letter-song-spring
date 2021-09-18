package com.kanozz.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

class KanoListenerImpl implements ApplicationListener<ContextRefreshedEvent> {


	private static final Logger log = LoggerFactory.getLogger(KanoListenerImpl.class);
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("start refresh");
	}
}
