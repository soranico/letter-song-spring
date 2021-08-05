package com.kanozz.beandefinition.callbackmethod;

import org.springframework.context.annotation.Bean;

class KanoConfig {

	@Bean(initMethod = "initBean")
	public KanoA kanoA(){
		return new KanoA();
	}
}
