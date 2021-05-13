package com.kanosd.construct;

import org.springframework.context.annotation.Bean;

public class KanoConfig {
	@Bean
	public KanoD kanoD(KanoB kanoB){
		return new KanoD(kanoB);
	}
}
