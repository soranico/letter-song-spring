package com.kanozz.asm;

import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.SpringAsmInfo;
import org.springframework.asm.Type;

import java.lang.annotation.Annotation;
import java.util.function.Consumer;

public class KanoAnnotationVisitor extends AnnotationVisitor {

	private Object source;

	private Class<Annotation> annotationClass;


	public KanoAnnotationVisitor(Object source, Class<Annotation> annotationClass) {
		super(SpringAsmInfo.ASM_VERSION);
		this.source = source;
		this.annotationClass = annotationClass;
	}

	@Override
	public void visit(String name, Object value) {
		super.visit(name, value);
	}

	@Override
	public void visitEnd() {
		super.visitEnd();
	}


	protected static AnnotationVisitor create(Object source, String descriptor,
											 Consumer<KanoAnnotationVisitor> consumer){
		String className = Type.getType(descriptor).getClassName();
		try {
			Class<Annotation> annotationClass = (Class<Annotation>) Class.forName(className);
			KanoAnnotationVisitor annotationVisitor = new KanoAnnotationVisitor(source, annotationClass);
			consumer.accept(annotationVisitor);
			return annotationVisitor;
		}catch (Exception e){

		}
		return null;
	}
}
