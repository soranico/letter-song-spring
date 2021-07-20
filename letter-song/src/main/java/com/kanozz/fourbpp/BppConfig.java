package com.kanozz.fourbpp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.kanozz.fourbpp")
@EnableAspectJAutoProxy
public class BppConfig {

	@Bean
	public BppA bppA(){
		return new BppA();
	}
}
