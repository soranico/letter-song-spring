package com.kanozz.factorybean;

import org.springframework.beans.factory.FactoryBean;

class KanoFactoryBeanA implements FactoryBean<KanoA> {
	@Override
	public KanoA getObject() throws Exception {
		return new KanoA();
	}

	@Override
	public Class<?> getObjectType() {
		return KanoA.class;
	}
}
