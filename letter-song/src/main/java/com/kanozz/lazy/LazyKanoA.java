package com.kanozz.lazy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;


class LazyKanoA {

	@Autowired
	@Lazy
	private LazyKanoB lazyKanoB;




	public LazyKanoB getLazyKanoB() {
		return lazyKanoB;
	}
}
