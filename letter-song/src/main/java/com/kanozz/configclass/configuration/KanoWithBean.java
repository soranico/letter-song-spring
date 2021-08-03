package com.kanozz.configclass.configuration;

import org.springframework.context.annotation.Bean;


class KanoWithBean {

	@Bean
	public KanoA kanoA(){
		return new KanoA();
	}

}
