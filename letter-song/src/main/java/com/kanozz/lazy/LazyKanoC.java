package com.kanozz.lazy;

import org.springframework.beans.factory.annotation.Autowired;

class LazyKanoC {

	@Autowired
	private LazyKanoB lazyKanoB;

	public LazyKanoB getLazyKanoB() {
		return lazyKanoB;
	}
}
