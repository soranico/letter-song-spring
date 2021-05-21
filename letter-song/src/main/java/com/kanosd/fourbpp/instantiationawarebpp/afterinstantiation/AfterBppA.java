package com.kanosd.fourbpp.instantiationawarebpp.afterinstantiation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class AfterBppA  {

	public AfterBppA(){
		log.info("newInstance {}",AfterBppA.class.getSimpleName());
	}

	@Autowired
	private AfterBppB afterBppB;


	private AfterBppC afterBppC;

	public void setAfterBppC(AfterBppC afterBppC) {
		this.afterBppC = afterBppC;
	}

	@Override
	public String toString() {
		return "AfterBppA{" +
				"afterBppB=" + afterBppB +
				", afterBppC=" + afterBppC +
				'}';
	}
}
