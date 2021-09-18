package com.kanozz.beandefinition.checkdependency;

import java.io.InputStream;

class KanoC {

	private KanoB kanoB;

	private String kanoStr;

	private int kano;

	private InputStream resource;

	public void setKanoB(KanoB kanoB) {
		this.kanoB = kanoB;
	}

	public void setKanoStr(String kanoStr) {
		this.kanoStr = kanoStr;
	}

	public void setKano(int kano) {
		this.kano = kano;
	}

	public void setResource(InputStream resource) {
		this.resource = resource;
	}
}
