package com.kanozz.asm;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.ClassReader;
import org.springframework.asm.SpringAsmInfo;

import java.io.InputStream;
import java.util.List;

public class TestAsm {

	private static final Logger log = LoggerFactory.getLogger(TestAsm.class);
	private static final int PARSING_OPTIONS = ClassReader.SKIP_DEBUG
			| ClassReader.SKIP_CODE | ClassReader.SKIP_FRAMES;


	/**
	 *
	 *
	 *
	 *
	 *
	 */
	@Test
	public void testVisitorClass() throws Exception {
		KanoClassVisitor classVisitor = new KanoClassVisitor(SpringAsmInfo.ASM_VERSION);

		InputStream classStream = Thread.currentThread().getContextClassLoader()
				.getResource("com/kanozz/asm/KanoA.class").openStream();
		ClassReader classReader = new ClassReader(classStream);
		classReader.accept(classVisitor,PARSING_OPTIONS);

		List<AnnotationVisitor> annotations = classVisitor.getAnnotations();

	}


}
