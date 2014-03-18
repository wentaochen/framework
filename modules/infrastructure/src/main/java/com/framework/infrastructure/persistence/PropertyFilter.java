package com.framework.infrastructure.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.framework.infrastructure.utils.ConvertUtils;
import com.framework.infrastructure.utils.ServletUtils;

/**
 * �����ORMʵ���޹ص����Թ���������װ��.
 * 
 * PropertyFilter��Ҫ��¼ҳ���м򵥵�������������,��Hibernate��CriterionҪ��.
 * 
 * @author calvin
 */
public class PropertyFilter {

	/** ������Լ�OR��ϵ�ķָ���. */
	public static final String OR_SEPARATOR = "_OR_";

	/** ���ԱȽ�����. */
	public enum MatchType {
		EQ, LIKE, LT, GT, LE, GE, ISNULL, ISNOTNULL;
		;
	}

	/** ������������.R����չ��,��ʾShort���� */
	public enum PropertyType {
		S(String.class), I(Integer.class), L(Long.class), N(Double.class), D(Date.class), B(Boolean.class), R(
				Short.class);

		private Class<?> clazz;

		private PropertyType(Class<?> clazz) {
			this.clazz = clazz;
		}

		public Class<?> getValue() {
			return clazz;
		}
	}

	private MatchType matchType = null;

	private Object matchValue = null;

	private Class<?> propertyClass = null;

	private String[] propertyNames = null;

	public PropertyFilter() {
	}

	/**
	 * @param filterName
	 *            �Ƚ������ַ���,�����ȽϵıȽ����͡�����ֵ���ͼ������б�. eg. LIKES_NAME_OR_LOGIN_NAME
	 * @param value
	 *            ���Ƚϵ�ֵ.
	 */
	public PropertyFilter(final String filterName, final String value) {
		// �����ѯ�����ж��Ƿ�Ϊ��
		if (StringUtils.containsIgnoreCase(filterName, "NULL")) {
			propertyNames = new String[1];
			propertyNames[0] = StringUtils.substringAfterLast(filterName, "_");

			// isNull
			if (StringUtils.containsIgnoreCase(value, "isNull")) {
				matchType = PropertyFilter.MatchType.ISNULL;

				return;
			}
			// isNotNull
			matchType = PropertyFilter.MatchType.ISNOTNULL;

			return;
		}

		String firstPart = StringUtils.substringBefore(filterName, "_");
		String matchTypeCode = StringUtils.substring(firstPart, 0, firstPart.length() - 1);
		String propertyTypeCode = StringUtils.substring(firstPart, firstPart.length() - 1, firstPart.length());

		try {
			matchType = Enum.valueOf(MatchType.class, matchTypeCode);
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter����" + filterName + "û�а������д,�޷��õ����ԱȽ�����.", e);
		}

		try {
			propertyClass = Enum.valueOf(PropertyType.class, propertyTypeCode).getValue();
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter����" + filterName + "û�а������д,�޷��õ�����ֵ����.", e);
		}

		String propertyNameStr = StringUtils.substringAfter(filterName, "_");
		Assert.isTrue(StringUtils.isNotBlank(propertyNameStr), "filter����" + filterName + "û�а������д,�޷��õ���������.");
		propertyNames = StringUtils.splitByWholeSeparator(propertyNameStr, PropertyFilter.OR_SEPARATOR);

		this.matchValue = ConvertUtils.convertStringToObject(value, propertyClass);
	}

	/**
	 * ��HttpRequest�д���PropertyFilter�б�, Ĭ��Filter������ǰ׺Ϊfilter.
	 * 
	 * @see #buildFromHttpRequest(HttpServletRequest, String)
	 */
	public static List<PropertyFilter> buildFromHttpRequest(final HttpServletRequest request) {
		return buildFromHttpRequest(request, "filter");
	}

	/**
	 * ��HttpRequest�д���PropertyFilter�б�
	 * PropertyFilter��������ΪFilter����ǰ׺_�Ƚ�������������_������.
	 * 
	 * eg. filter_EQS_name filter_LIKES_name_OR_email
	 */
	@SuppressWarnings("unchecked")
	public static List<PropertyFilter> buildFromHttpRequest(final HttpServletRequest request, final String filterPrefix) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();

		// ��request�л�ȡ������ǰ׺���Ĳ���,����ȥ��ǰ׺����Ĳ���Map.
		Map<String, Object> filterParamMap = ServletUtils.getParametersStartingWith(request, filterPrefix + "_");

		// ��������Map,����PropertyFilter�б�
		for (Map.Entry<String, Object> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = (String) entry.getValue();
			// ���valueֵΪ��,����Դ�filter.
			if (StringUtils.isNotBlank(value)) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}

		return filterList;
	}

	/**
	 * ��ȡ�Ƚ�ֵ������.
	 */
	public Class<?> getPropertyClass() {
		return propertyClass;
	}

	/**
	 * ��ȡ�ȽϷ�ʽ.
	 */
	public MatchType getMatchType() {
		return matchType;
	}

	/**
	 * ��ȡ�Ƚ�ֵ.
	 */
	public Object getMatchValue() {
		return matchValue;
	}

	/**
	 * ��ȡ�Ƚ����������б�.
	 */
	public String[] getPropertyNames() {
		return propertyNames;
	}

	/**
	 * ��ȡΨһ�ıȽ���������.
	 */
	public String getPropertyName() {
		Assert.isTrue(propertyNames.length == 1, "There are not only one property in this filter.");
		return propertyNames[0];
	}

	/**
	 * �Ƿ�Ƚ϶������.
	 */
	public boolean hasMultiProperties() {
		return (propertyNames.length > 1);
	}
}
