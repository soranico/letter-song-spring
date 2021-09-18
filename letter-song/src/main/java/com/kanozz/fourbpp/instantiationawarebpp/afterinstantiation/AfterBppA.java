package com.kanozz.fourbpp.instantiationawarebpp.afterinstantiation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

class AfterBppA  {
	private static final Logger log = LoggerFactory.getLogger(AfterBppA.class);
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
