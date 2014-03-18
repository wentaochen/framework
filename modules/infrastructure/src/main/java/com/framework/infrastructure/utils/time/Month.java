package com.framework.infrastructure.utils.time;

import java.util.Calendar;
import java.util.Date;

/**
 * <pre>
 * @author chenwentao
 *
 * @version 0.9
 *
 * 修改版本: 0.9
 * 修改日期: Feb 27, 2011
 * 修改人 :  chenwentao
 * 修改说明: 初步完成
 * 复审人 ：
 * </pre>
 */
public class Month {

	/**
	 * 第一天
	 */
	protected String firstDay;

	/**
	 * 最后一天
	 */
	protected String lastDay;

	/**
	 * 第几个月
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
	 * 得到本月的第一天
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
	 * 得到本月的最后一天
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
