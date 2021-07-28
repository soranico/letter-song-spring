package com.kanozz.construct;

import org.springframework.beans.factory.annotation.Autowired;

class KanoA {

	private KanoB kanoB;

	@Autowired
	private KanoC kanoC;

	public KanoA(KanoB kanoB) {
		this.kanoB = kanoB;
	}

	@Override
	public String toString() {
		return "KanoA{" +
				"kanoB=" + kanoB +
				", kanoC=" + kanoC +
				'}';
	}
}
