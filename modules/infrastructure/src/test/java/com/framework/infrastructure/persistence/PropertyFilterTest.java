package com.framework.infrastructure.persistence;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import com.framework.infrastructure.persistence.PropertyFilter.MatchType;

/**
 * PropertyFilter�Ĳ�����
 * 
 * @author calvin
 */
public class PropertyFilterTest {

	@Test
	public void createFilter() {
		// Boolean EQ filter
		PropertyFilter booleanEqFilter = new PropertyFilter("EQB_foo", "true");
		assertEquals(MatchType.EQ, booleanEqFilter.getMatchType());
		assertEquals(Boolean.class, booleanEqFilter.getPropertyClass());
		assertEquals(true, booleanEqFilter.getMatchValue());

		// Date LT filter
		PropertyFilter dateLtFilter = new PropertyFilter("LTD_foo",
				"2046-01-01");
		assertEquals(MatchType.LT, dateLtFilter.getMatchType());
		assertEquals(Date.class, dateLtFilter.getPropertyClass());
		assertEquals("foo", dateLtFilter.getPropertyName());
		assertEquals(new DateTime(2046, 1, 1, 0, 0, 0, 0).toDate(),
				dateLtFilter.getMatchValue());

		// Integer GT filter
		PropertyFilter intGtFilter = new PropertyFilter("GTI_foo", "123");
		assertEquals(MatchType.GT, intGtFilter.getMatchType());
		assertEquals(Integer.class, intGtFilter.getPropertyClass());
		assertEquals("foo", intGtFilter.getPropertyName());
		assertEquals(123, intGtFilter.getMatchValue());

		// Double LE filter
		PropertyFilter doubleLeFilter = new PropertyFilter("LEN_foo", "12.33");
		assertEquals(MatchType.LE, doubleLeFilter.getMatchType());
		assertEquals(Double.class, doubleLeFilter.getPropertyClass());
		assertEquals("foo", doubleLeFilter.getPropertyName());
		assertEquals(12.33, doubleLeFilter.getMatchValue());

		// Long GE filter
		PropertyFilter longGeFilter = new PropertyFilter("GEL_foo", "123456789");
		assertEquals(MatchType.GE, longGeFilter.getMatchType());
		assertEquals(Long.class, longGeFilter.getPropertyClass());
		assertEquals("foo", longGeFilter.getPropertyName());
		assertEquals(123456789L, longGeFilter.getMatchValue());

		// Like OR filter
		PropertyFilter likeOrFilter = new PropertyFilter("LIKES_foo_OR_bar",
				"hello");
		assertEquals(MatchType.LIKE, likeOrFilter.getMatchType());
		assertEquals(String.class, likeOrFilter.getPropertyClass());
		assertArrayEquals(new String[] { "foo", "bar" }, likeOrFilter
				.getPropertyNames());
		assertEquals("hello", likeOrFilter.getMatchValue());

		// Is NULL
		PropertyFilter isNullFilter = new PropertyFilter("NULL_x", "isNull_x");
		assertEquals(MatchType.ISNULL, isNullFilter.getMatchType());
		assertEquals(null, isNullFilter.getPropertyClass());
		assertArrayEquals(new String[] { "x" }, isNullFilter.getPropertyNames());
		assertEquals(null, isNullFilter.getMatchValue());

		// IsNot null
		PropertyFilter isNotNullFilter = new PropertyFilter("NULL_x",
				"isNotNull_x");
		assertEquals(MatchType.ISNOTNULL, isNotNullFilter.getMatchType());
		assertEquals(null, isNotNullFilter.getPropertyClass());
		assertArrayEquals(new String[] { "x" }, isNotNullFilter
				.getPropertyNames());
		assertEquals(null, isNotNullFilter.getMatchValue());

		// Short EQ
		PropertyFilter shortEq = new PropertyFilter("EQR_x", "0");
		assertEquals(MatchType.EQ, shortEq.getMatchType());
		assertEquals(Short.class, shortEq.getPropertyClass());
		assertEquals("x", shortEq.getPropertyName());
		assertEquals(Short.valueOf("0"), shortEq.getMatchValue());

	}

	@Test
	public void createErrorFilter() throws Exception {
		int exceptionCount = 0;
		try {
			new PropertyFilter("ABS_foo", "hello");
		}
		catch (IllegalArgumentException e) {
			exceptionCount++;
		}

		try {
			new PropertyFilter("GEX_foo", "hello");
		}
		catch (IllegalArgumentException e) {
			exceptionCount++;
		}

		try {
			new PropertyFilter("EQS_", "hello");
		}
		catch (IllegalArgumentException e) {
			exceptionCount++;
		}

		assertEquals(3, exceptionCount);
	}

	@Test
	public void buildPropertyFiltersFromHttpRequest() {
		// normal case
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("filter_EQS_loginName", "abcd");
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);

		assertEquals(1, filters.size());
		PropertyFilter filter = filters.get(0);
		assertEquals(PropertyFilter.MatchType.EQ, filter.getMatchType());
		assertEquals("loginName", filter.getPropertyName());
		assertEquals(String.class, filter.getPropertyClass());
		assertEquals("abcd", filter.getMatchValue());

		// filter prefix name is prefix
		request = new MockHttpServletRequest();
		request.setParameter("prefix_EQS_loginName", "abcd");
		filters = PropertyFilter.buildFromHttpRequest(request, "prefix");
		assertEquals(1, filters.size());
		assertEquals(PropertyFilter.MatchType.EQ, filters.get(0).getMatchType());

		// ignore filter without value
		request = new MockHttpServletRequest();
		request.setParameter("filter_EQS_loginName", "");
		filters = PropertyFilter.buildFromHttpRequest(request);
		assertEquals(0, filters.size());

	}
}
