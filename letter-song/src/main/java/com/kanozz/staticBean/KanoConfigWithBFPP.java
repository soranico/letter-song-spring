package com.kanozz.staticBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class KanoConfigWithBFPP {

	@Bean
	public KanoBeanFactoryPostProcessor kanoBeanFactoryPostProcessor(){
		return new KanoBeanFactoryPostProcessor();
	}
}
