/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.framework.infrastructure.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * �����쳣�Ĺ�����.
 * 
 */
public class Exceptions {

	private Exceptions() {
	}

	/**
	 * ��CheckedExceptionת��ΪUncheckedException.
	 */
	public static RuntimeException unchecked(Exception e) {
		if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		} else {
			return new RuntimeException(e);
		}
	}

	/**
	 * ��ErrorStackת��ΪString.
	 */
	public static String getStackTraceAsString(Exception e) {
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}
}
