/*
 * @(#)Hour.java 2011-11-29
 */

package com.framework.infrastructure.utils.time;

/**
 * <pre>
 * @author chenwentao
 *
 * @version 0.9
 *
 * �޸İ汾: 0.9
 * �޸�����: 2011-11-29
 * �޸��� :  chenwentao
 * �޸�˵��: �������
 * ������ ��
 * </pre>
 */
public class Hour {

	private String begin;

	private String end;

	public Hour(String begin, String end) {
		this.begin = begin;
		this.end = end;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
}
