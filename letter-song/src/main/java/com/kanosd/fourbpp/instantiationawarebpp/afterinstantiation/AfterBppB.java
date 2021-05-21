package com.kanosd.fourbpp.instantiationawarebpp.afterinstantiation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class AfterBppB {

	private AfterBppC afterBppC;

	public void setAfterBppC(AfterBppC afterBppC) {
		this.afterBppC = afterBppC;
	}

	public AfterBppB(){
		log.info("newInstance {}",AfterBppB.class.getSimpleName());
	}


	@Override
	public String toString() {
		return "AfterBppB{" +
				"afterBppC=" + afterBppC +
				'}';
	}
}
