package com.kanozz.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class KanoListenerAnnotation{

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("custom method listener");
	}


	@EventListener
	public static void onStaticApplicationEvent(ContextRefreshedEvent event) {
		log.info("static method listener");
	}


}
