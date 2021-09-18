package com.kanozz.fourbpp.instantiationawarebpp.afterinstantiation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class AfterBppC {
	private static final Logger log = LoggerFactory.getLogger(AfterBppC.class);
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
