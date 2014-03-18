/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.framework.infrastructure.mapper;

import java.util.Collection;
import java.util.List;

import org.dozer.DozerBeanMapper;

import com.google.common.collect.Lists;

/**
 * �?��封装Dozer, 实现深度转换Bean<->Bean的Mapper.实现:
 *  
 * 1. 持有Mapper的单�? 
 * 2. 泛型返回值转�?
 * 3. 批量转换Collection中的�?��对象.
 * 4. 区分创建新的B对象与将对象A值复制到已存在的B对象两种函数.
 * 
 * @author calvin
 */
public class BeanMapper {

	/**
	 * 持有Dozer单例, 避免重复创建DozerMapper消�?资源.
	 */
	private static DozerBeanMapper dozer = new DozerBeanMapper();

	private BeanMapper() {
	}

	/**
	 * 基于Dozer转换对象的类�?
	 */
	public static <T> T map(Object source, Class<T> destinationClass) {
		return dozer.map(source, destinationClass);
	}

	/**
	 * 基于Dozer转换Collection中对象的类型.
	 */
	public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
		List<T> destinationList = Lists.newArrayList();
		for (Object sourceObject : sourceList) {
			T destinationObject = dozer.map(sourceObject, destinationClass);
			destinationList.add(destinationObject);
		}
		return destinationList;
	}

	/**
	 * 基于Dozer将对象A的�?拷贝到对象B�?
	 */
	public static void copy(Object source, Object destinationObject) {
		dozer.map(source, destinationObject);
	}
}