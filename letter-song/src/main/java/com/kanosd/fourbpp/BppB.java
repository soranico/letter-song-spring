package com.kanosd.fourbpp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BppB {
	@Autowired
	private BppA bppA;

	@Override
	public String toString() {
		return "BppB{" +
				"bppA=" + bppA +
				'}';
	}
}
