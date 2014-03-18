/**
 * Copyright (c) 2011-20012 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Id StringUtilsTest.java 上午9:41:52 chenwentao
 */
package com.framework.infrastructure.utils;

import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 *
 * @author chenwentao
 *
 */
public class StringUtilsTest {

	@Test
	public void utilsByApache() {
		//join
		List<String> inputList = Lists.newArrayList("a", "b", "c");
		String result = StringUtils.join(inputList, ",");
		Assert.assertEquals("a,b,c", result);

		Assert.assertEquals("001", StringUtils.leftPad("1", 3, "0"));
	}

	@Test
	public void joinByGuava() {

		//忽略Null值。
		String[] fantasyGenres = { "Space Opera", null, "Horror", "Magic realism", null, "Religion" };
		String joined = Joiner.on(", ").skipNulls().join(fantasyGenres);
		System.out.println(joined);

		//将Null值转换为特定字符串.
		String[] fantasyGenres2 = { "Space Opera", null, "Horror", "Magic realism", null, "Religion" };
		joined = Joiner.on(", ").useForNull("NULL!!!").join(fantasyGenres2);
		System.out.println(joined);

		//join Map类型
		Map<Integer, String> map = Maps.newHashMap();
		map.put(1, "Space Opera");
		map.put(2, "Horror");
		map.put(3, "Magic realism");
		joined = Joiner.on(",").withKeyValueSeparator(":").join(map);
		System.out.println(joined);

		//append一个已存在的字符串
		StringBuilder sb = new StringBuilder("StringBulder Demo: ");
		joined = Joiner.on(", ").skipNulls().appendTo(sb, fantasyGenres).toString();
		System.out.println(joined);
	}
}
