package com.kanosd.construct;

import org.springframework.beans.factory.annotation.Autowired;

public class KanoD {
	private KanoB kanoB;

	@Autowired
	private KanoC kanoC;

	public KanoD(KanoB kanoB) {
		this.kanoB = kanoB;
	}

	@Override
	public String toString() {
		return "KanoD{" +
				"kanoB=" + kanoB +
				", kanoC=" + kanoC +
				'}';
	}
}
