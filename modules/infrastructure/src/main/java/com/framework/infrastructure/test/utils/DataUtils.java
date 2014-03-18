/**
 * Copyright (c) 2005-2010 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: DataUtils.java 1185 2010-08-29 15:56:19Z calvinxiu $
 */
package com.framework.infrastructure.test.utils;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * �����������ɹ�����.
 * 
 * @author calvin
 */
public class DataUtils {
	private static Random random = new Random();

	/**
	 * �������ID.
	 */
	public static long randomId() {
		return random.nextLong();
	}

	/**
	 * �����������, prefix�ַ���+5λ�������.
	 */
	public static String randomName(String prefix) {
		return prefix + random.nextInt(10000);
	}

	/**
	 * ������list���������һ������.
	 */
	public static <T> T randomOne(List<T> list) {
		return randomSome(list, 1).get(0);
	}

	/**
	 * ������list������������������.
	 */
	public static <T> List<T> randomSome(List<T> list) {
		return randomSome(list, random.nextInt(list.size()));
	}

	/**
	 * ������list���������count������.
	 */
	public static <T> List<T> randomSome(List<T> list, int count) {
		Collections.shuffle(list);
		return list.subList(0, count);
	}
}
