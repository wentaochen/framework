package com.framework.infrastructure.test.groups;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ʵ��TestNG Groups����ִ���������ܵ�annotation.
 * 
 * @author freeman
 * @author calvin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD, ElementType.TYPE })
@Documented
public @interface Groups {

	/**
	 * ִ���������Ĳ���.
	 */
	String ALL = "all";

	/**
	 * �����,Ĭ��ΪALL.
	 */
	String value() default ALL;
}
