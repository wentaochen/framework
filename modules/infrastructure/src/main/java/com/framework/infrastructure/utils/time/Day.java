/*
 * @(#)Day.java 2011-11-29
 */

package com.framework.infrastructure.utils.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * <pre>
 * @author chenwentao
 *
 * @version 0.9
 *
 * 修改版本: 0.9
 * 修改日期: 2011-11-29
 * 修改人 :  chenwentao
 * 修改说明: 初步完成
 * 复审人 ：
 * </pre>
 */
public class Day {

	private static final String HH00 = ":00:00";

	private static final String HH59 = ":59:59";

	private static final String BLANK = " ";

	private List<Hour> hours = Lists.newArrayList();

	private Date time;

	public static List<Hour> getHours(Date date, String dateFormat) {
		return parse(date, dateFormat);
	}

	/**
	 * 移动天数,正数表示+天，负数表示-天
	 * 
	 * @param date
	 * @param dateFormat
	 * @param number
	 * @return
	 */
	public static Date moveDay(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);

		return cal.getTime();
	}

	/**
	 * @param date
	 * @param dateFormat
	 */
	private static List<Hour> parse(Date date, String dateFormat) {
		List<Hour> hours = Lists.newArrayList();
		DateFormat format = new SimpleDateFormat(dateFormat);
		String today = format.format(date);
		for (int i = 0; i < 24; i++) {
			String hh = (i < 10 ? "0" + i : String.valueOf(i));
			String hourBegin = today + BLANK + hh + HH00;
			String hourEnd = today + BLANK + hh + HH59;
			Hour hour = new Hour(hourBegin, hourEnd);
			hours.add(hour);
		}
		return hours;
	}

	/**
	 * @param date
	 * @param dateFormat
	 *            一般是yyyy-MM-dd HH:mm:ss
	 */
	public Day(Date date, String dateFormat) {
		this.time = date;
		this.hours = parse(date, dateFormat);
	}

	public List<Hour> getHours() {
		return hours;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
