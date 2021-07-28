package com.kanozz.bfpp;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestBeanFactoryPostProcessor {

	/**
	 * api注册的一定首先执行
	 *
	 * PriorityOrdered和Ordered的排序只对本轮有效
	 *
	 * BDRPP和BFPP的执行顺序只对本轮有效
	 *
	 * 如果存在 A_BDRPP B_BFPP  被扫描出来
	 * 则首先会执行 A_BDRPP,在 A_BDRPP 执行完后会再次获取所有实现BFPP的BD
	 * 上轮执行添加的BD这次会被扫描出来
	 * 1. A_BDRPP 执行完没有新的 BDRPP , 那么会执行 B_BFPP
	 * 1.1 B_BFPP执行完有新的 BDRPP,那么下轮会先执行 BDRPP
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 */
	@Test
	public void testBFPPPriority(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.kanozz.bfpp.scan");
		context.refresh();
	}

}
