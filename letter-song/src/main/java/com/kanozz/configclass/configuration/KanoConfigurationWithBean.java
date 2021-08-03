package com.kanozz.configclass.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class KanoConfigurationWithBean {

	@Bean
	public KanoA kanoA(){
		return new KanoA();
	}


}
