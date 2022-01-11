package com.kanozz.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class KanoConfig {


	@Bean
	public KanoA kanoA(){
		return new KanoA();
	}

	@Bean
	public static KanoB kanoB(){
		return new KanoB();
	}

}
