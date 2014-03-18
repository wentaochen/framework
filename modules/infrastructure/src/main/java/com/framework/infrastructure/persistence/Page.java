package com.framework.infrastructure.persistence;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;


/**
 * �����ORMʵ���޹صķ�ҳ��������ѯ�����װ.
 * 
 * ע��������Ŵ�1��ʼ.
 * 
 * @param <T> Page�м�¼������.
 * 
 * @author calvin
 */
public class Page<T> {
	//-- �������� --//
	public static final String ASC = "asc";
	public static final String DESC = "desc";

	//-- ��ҳ���� --//
	protected int pageNo = 1;
	protected int pageSize = -1;
	protected String orderBy = null;
	protected String order = null;
	protected boolean autoCount = true;

	//-- ���ؽ�� --//
	protected List<T> result = Lists.newArrayList();
	protected long totalCount = -1;

	//-- ���캯�� --//
	public Page() {
	}

	public Page(int pageSize) {
		this.pageSize = pageSize;
	}

	//-- ��ҳ�������ʺ��� --//
	/**
	 * ��õ�ǰҳ��ҳ��,��Ŵ�1��ʼ,Ĭ��Ϊ1.
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * ���õ�ǰҳ��ҳ��,��Ŵ�1��ʼ,����1ʱ�Զ�����Ϊ1.
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	/**
	 * ����Page���������setPageNo����,�������������á�
	 */
	public Page<T> pageNo(final int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}

	/**
	 * ���ÿҳ�ļ�¼����, Ĭ��Ϊ-1.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * ����ÿҳ�ļ�¼����.
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * ����Page���������setPageSize����,�������������á�
	 */
	public Page<T> pageSize(final int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}

	/**
	 * ����pageNo��pageSize���㵱ǰҳ��һ����¼���ܽ�����е�λ��,��Ŵ�1��ʼ.
	 */
	public int getFirst() {
		return ((pageNo - 1) * pageSize) + 1;
	}

	/**
	 * ��������ֶ�,��Ĭ��ֵ. ��������ֶ�ʱ��','�ָ�.
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * ���������ֶ�,��������ֶ�ʱ��','�ָ�.
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * ����Page���������setOrderBy����,�������������á�
	 */
	public Page<T> orderBy(final String theOrderBy) {
		setOrderBy(theOrderBy);
		return this;
	}

	/**
	 * ���������, ��Ĭ��ֵ.
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * ��������ʽ��.
	 * 
	 * @param order ��ѡֵΪdesc��asc,��������ֶ�ʱ��','�ָ�.
	 */
	public void setOrder(final String order) {
		String lowcaseOrder = StringUtils.lowerCase(order);

		//���order�ַ����ĺϷ�ֵ
		String[] orders = StringUtils.split(lowcaseOrder, ',');
		for (String orderStr : orders) {
			if (!StringUtils.equals(DESC, orderStr) && !StringUtils.equals(ASC, orderStr)) {
				throw new IllegalArgumentException("������" + orderStr + "���ǺϷ�ֵ");
			}
		}

		this.order = lowcaseOrder;
	}

	/**
	 * ����Page���������setOrder����,�������������á�
	 */
	public Page<T> order(final String theOrder) {
		setOrder(theOrder);
		return this;
	}

	/**
	 * �Ƿ������������ֶ�,��Ĭ��ֵ.
	 */
	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(order));
	}

	/**
	 * ��ò�ѯ����ʱ�Ƿ����Զ�ִ��count��ѯ��ȡ�ܼ�¼��, Ĭ��Ϊfalse.
	 */
	public boolean isAutoCount() {
		return autoCount;
	}

	/**
	 * ���ò�ѯ����ʱ�Ƿ��Զ���ִ��count��ѯ��ȡ�ܼ�¼��.
	 */
	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	/**
	 * ����Page���������setAutoCount����,�������������á�
	 */
	public Page<T> autoCount(final boolean theAutoCount) {
		setAutoCount(theAutoCount);
		return this;
	}

	//-- ���ʲ�ѯ������� --//

	/**
	 * ���ҳ�ڵļ�¼�б�.
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * ����ҳ�ڵļ�¼�б�.
	 */
	public void setResult(final List<T> result) {
		this.result = result;
	}

	/**
	 * ����ܼ�¼��, Ĭ��ֵΪ-1.
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * �����ܼ�¼��.
	 */
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * ����pageSize��totalCount������ҳ��, Ĭ��ֵΪ-1.
	 */
	public long getTotalPages() {
		if (totalCount < 0) {
			return -1;
		}

		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	/**
	 * �Ƿ�����һҳ.
	 */
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * ȡ����ҳ��ҳ��, ��Ŵ�1��ʼ.
	 * ��ǰҳΪβҳʱ�Է���βҳ���.
	 */
	public int getNextPage() {
		if (isHasNext()) {
			return pageNo + 1;
		} else {
			return pageNo;
		}
	}

	/**
	 * �Ƿ�����һҳ.
	 */
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * ȡ����ҳ��ҳ��, ��Ŵ�1��ʼ.
	 * ��ǰҳΪ��ҳʱ������ҳ���.
	 */
	public int getPrePage() {
		if (isHasPre()) {
			return pageNo - 1;
		} else {
			return pageNo;
		}
	}
}