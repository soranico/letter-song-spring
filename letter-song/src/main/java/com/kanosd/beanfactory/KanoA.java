package com.kanosd.beanfactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KanoA {

	// @Autowired 显示说明需要属性注入,修改注入模型不需要此注解
	private KanoB kanoB;


	public void setKanoB(KanoB kano){
		this.kanoB = kanoB;
	}

	@Override
	public String toString() {
		return "KanoA{" +
				"kanoB=" + kanoB +
				'}';
	}
}
