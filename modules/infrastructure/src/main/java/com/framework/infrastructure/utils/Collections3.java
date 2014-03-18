/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.framework.infrastructure.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Collections���߼�.
 * ��JDK��Collections��Guava��Collections2��, ����ΪCollections3.
 * 
 */
public class Collections3 {

	private Collections3() {
	}

	/**
	 * ����a-b�ļ���.
	 */
	public static <T> List<T> subtract(final Collection<T> a, final Collection<T> b) {
		ArrayList<T> list = new ArrayList<T>(a);
		for (Object element : b) {
			list.remove(element);
		}
		return list;
	}

	/**
	 * ��ȡ�����еĶ��������(ͨ��Getter����), ��ϳ�Map.
	 * 
	 * @param collection ��Դ����.
	 * @param keyPropertyName Ҫ��ȡΪMap�е�Keyֵ��������.
	 * @param valuePropertyName Ҫ��ȡΪMap�е�Valueֵ��������.
	 */
	public static Map extractToMap(final Collection collection, final String keyPropertyName,
			final String valuePropertyName) {
		Map map = new HashMap(collection.size());

		try {
			for (Object obj : collection) {
				map.put(PropertyUtils.getProperty(obj, keyPropertyName),
						PropertyUtils.getProperty(obj, valuePropertyName));
			}
		} catch (Exception e) {
			throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
		}

		return map;
	}

	/**
	 * ��ȡ�����еĶ��������(ͨ��Getter����), ��ϳ�List.
	 * 
	 * @param collection ��Դ����.
	 * @param propertyName Ҫ��ȡ��������.
	 */
	public static List extractToList(final Collection collection, final String propertyName) {
		List list = new ArrayList(collection.size());

		try {
			for (Object obj : collection) {
				list.add(PropertyUtils.getProperty(obj, propertyName));
			}
		} catch (Exception e) {
			throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
		}

		return list;
	}

	/**
	 * ��ȡ�����еĶ��������(ͨ��Getter����), ��ϳ��ɷָ���ָ����ַ���.
	 * 
	 * @param collection ��Դ����.
	 * @param propertyName Ҫ��ȡ��������.
	 * @param separator �ָ���.
	 */
	public static String extractToString(final Collection collection, final String propertyName, final String separator) {
		List list = extractToList(collection, propertyName);
		return StringUtils.join(list, separator);
	}

	/**
	 * ת��CollectionΪString, �м��� separator�ָ���
	 */
	public static String convertToString(final Collection collection, final String separator) {
		return StringUtils.join(collection, separator);
	}

	/**
	 * ת��CollectionΪString, ÿ��Ԫ�ص�ǰ�����prefix���������postfix����<div>mymessage</div>��
	 */
	public static String convertToString(final Collection collection, final String prefix, final String postfix) {
		StringBuilder builder = new StringBuilder();
		for (Object o : collection) {
			builder.append(prefix).append(o).append(postfix);
		}
		return builder.toString();
	}
}
