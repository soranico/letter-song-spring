package com.kanozz.beanfactory.ingore;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextAware;

@Slf4j
class KanoA {
	private static final Logger log = LoggerFactory.getLogger(KanoA.class);
	// @Autowired 显示说明需要属性注入,修改注入模型不需要此注解
	private KanoB kanoB;

	/**
	 * 不会被自动注入
	 * 除非显示标记需要注入
	 */
	private ApplicationContextAware applicationContextAware;

	public void setApplicationContextAware(ApplicationContextAware applicationContextAware) {
		this.applicationContextAware = applicationContextAware;
	}

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
