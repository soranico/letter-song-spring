package com.kanozz.isconfigbean;

import org.springframework.context.annotation.Bean;

class IsConfigKanoC {


	@Bean
	public IsConfigInnerKanoC isConfigInnerKanoC(){
		return new IsConfigInnerKanoC();
	}

	private static class IsConfigInnerKanoC{

	}
}
