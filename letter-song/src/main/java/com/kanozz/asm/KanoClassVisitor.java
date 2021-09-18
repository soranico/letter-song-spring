package com.kanozz.asm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.ClassVisitor;

import java.util.ArrayList;
import java.util.List;

class KanoClassVisitor extends ClassVisitor {
	private static final Logger log = LoggerFactory.getLogger(KanoClassVisitor.class);
	private String className;

	private List<AnnotationVisitor> annotations = new ArrayList<>(2);

	public KanoClassVisitor(int api) {
		super(api);
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		this.className = name.replace('/','.');
	}

	@Override
	public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
		log.info("current annotation = {}",descriptor);
		return KanoAnnotationVisitor.create(this.className,descriptor,this.annotations::add);
	}


	public List<AnnotationVisitor> getAnnotations() {
		return annotations;
	}

	public String getClassName() {
		return className;
	}
}
