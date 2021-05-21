package com.kanosd.fourbpp.instantiationawarebpp.afterinstantiation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class AfterBppC {

	private AfterBppB afterBppB;
	public AfterBppC(AfterBppB afterBppB){
		this.afterBppB = afterBppB;
		log.info("newInstance {}",AfterBppC.class.getSimpleName());
	}

	@Override
	public String toString() {
		return "AfterBppC{" +
				"afterBppB=" + afterBppB +
				'}';
	}
}
