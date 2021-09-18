package com.kanozz.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class KanoListenerAnnotation{
	private static final Logger log = LoggerFactory.getLogger(KanoListenerAnnotation.class);
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("custom method listener");
	}


	@EventListener
	public static void onStaticApplicationEvent(ContextRefreshedEvent event) {
		log.info("static method listener");
	}


}
