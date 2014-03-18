package com.framework.infrastructure.utils.time;

import java.util.Calendar;
import java.util.Date;

/**
 * <pre>
 * @author chenwentao
 *
 * @version 0.9
 *
 * �޸İ汾: 0.9
 * �޸�����: Feb 27, 2011
 * �޸��� :  chenwentao
 * �޸�˵��: �������
 * ������ ��
 * </pre>
 */
public class Month {

	/**
	 * ��һ��
	 */
	protected String firstDay;

	/**
	 * ���һ��
	 */
	protected String lastDay;

	/**
	 * �ڼ�����
	 */
	protected int monthOfYear;

	public Month() {
	}

	public Month(Date strDate) {
		this(DateUtils.format(strDate), DateUtils.DEFAULT_DATE_FORMAT);
	}

	public Month(String strDate) {
		this(strDate, DateUtils.DEFAULT_DATE_FORMAT);
	}

	public Month(String strDate, String format) {
		Date now = DateUtils.parse(strDate);
		firstDay = DateUtils.format(getMonthFirstDay(now), format);
		lastDay = DateUtils.format(getMonthLastDay(now), format);

		monthOfYear = DateUtils.getMonthOfYear(now);
	}

	/**
	 * �õ����µĵ�һ��
	 * 
	 * @return
	 */
	public static Date getMonthFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMinimum(Calendar.DAY_OF_MONTH));

		return calendar.getTime();
	}

	/**
	 * �õ����µ����һ��
	 * 
	 * @return
	 */
	public static Date getMonthLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH));

		return calendar.getTime();
	}

	public String getFirstDay() {
		return firstDay;
	}

	public void setFirstDay(String firstDay) {
		this.firstDay = firstDay;
	}

	public String getLastDay() {
		return lastDay;
	}

	public void setLastDay(String lastDay) {
		this.lastDay = lastDay;
	}

	public int getMonthOfYear() {
		return monthOfYear;
	}

	public void setMonthOfYear(int monthOfYear) {
		this.monthOfYear = monthOfYear;
	}

}