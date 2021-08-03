package com.kanozz.configclass.parse.imported.selector;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestImportSelector {

	/**
	 *
	 * 对于Import的实现 ImportSelector 的类而言
	 * 这个类就是一个工具类,不会被当成一个bean
	 * 通过工具类导入的类,如果是一个普通类那么会
	 * 被当成bean来处理,否则也会当成工具人
	 * e.g
	 * @see KanoA noraml
	 * @see KanoB
	 *
	 * 注意
	 *
	 * 对于显示标记需要创建成bean的类而言,只要其被扫描
	 * 或被注册成BD,那么就会创建成bean
	 *
	 *
	 *
	 */
	@Test
	public void testImportSelector(){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(KanoConfiguration.class);
		context.refresh();
		KanoScanImportSelector kanoScanImportSelector = context.getBean(KanoScanImportSelector.class);

//		KanoImportSelector kanoImportSelector = context.getBean(KanoImportSelector.class);
	}
}
