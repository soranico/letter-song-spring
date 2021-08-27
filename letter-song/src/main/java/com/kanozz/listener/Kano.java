package com.kanozz.listener;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

interface Kano {

	@EventListener
	default void kano(ContextRefreshedEvent event){
		System.out.println("=======");
	}

	void onApplicationEvent(ContextRefreshedEvent event);
}
