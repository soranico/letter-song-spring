package com.kanozz.staticBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class KanoConfigWithBDFPP {

	@Bean
	public KanoBeanDefinitionRegistryPostProcessor kanoBeanDefinitionRegistryPostProcessor(){
		return new KanoBeanDefinitionRegistryPostProcessor();
	}
}
