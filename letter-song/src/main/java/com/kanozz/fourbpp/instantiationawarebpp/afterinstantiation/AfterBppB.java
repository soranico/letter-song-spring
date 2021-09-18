package com.kanozz.fourbpp.instantiationawarebpp.afterinstantiation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class AfterBppB {
	private static final Logger log = LoggerFactory.getLogger(AfterBppB.class);
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
