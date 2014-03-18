package com.framework.infrastructure.utils.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.DateTime;

/**
 * <pre>
 * 表示星期,包含周一至周日
 * 
 * @author chenwentao
 *
 * @version 0.9
 *
 * 修改版本: 0.9
 * 修改日期: Feb 26, 2011
 * 修改人 :  chenwentao
 * 修改说明: 初步完成
 * 复审人 ：
 * </pre>
 */
public class Week {

	protected String monday;

	protected String sunday;

	protected int weekOfYear;

	protected int weekOfMonth;

	/**
	 * 判断一个星期是否越界
	 */
	protected boolean isCross;

	/**
	 * 获得某一个周到所有日期
	 * 
	 * @param date
	 * @return
	 */
	public static List<Day> getDays(Date date) {
		DateTime time = new DateTime(date.getTime());
		Long value = time.dayOfWeek().withMinimumValue().toDate().getTime();
		DateTime fristDay = new DateTime(value);
		List<Day> result = new ArrayList<Day>();
		// 把星期一加上
		result.add(new Day(fristDay.toDate(),
				FormatterConstants.FROMAT_ONLY_DAY));
		// 加上剩余的6天
		for (int i = 1; i <= 6; i++) {
			result.add(new Day(fristDay.plusDays(i).toDate(),
					FormatterConstants.FROMAT_ONLY_DAY));
		}

		return result;
	}

	public Week(String stDate, String strFormat) throws ParseException {
		init(stDate, strFormat);
	}

	/**
	 * 根据当前给定的日期,初始化星期一和星期日,同时初始化是第几周
	 * 
	 * @param date
	 * @throws ParseException
	 */
	public void init(String stDate, String strFormat) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		Date date = sdf.parse(stDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
		weekOfMonth = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);

		int cuurentMonth = calendar.get(Calendar.MONTH);

		sunday = DateUtils.getWeek(date, "0");
		monday = DateUtils.getWeek(date, "1");

		calendar.setTime(sdf.parse(sunday));
		if (calendar.get(Calendar.MONTH) != cuurentMonth
				|| calendar.get(Calendar.MONTH) != cuurentMonth) {
			isCross = true;
		}
	}

	public String getMonday() {
		return monday;
	}

	public void setMonday(String monday) {
		this.monday = monday;
	}

	public String getSunday() {
		return sunday;
	}

	public void setSunday(String sunday) {
		this.sunday = sunday;
	}

	public int getWeekOfYear() {
		return weekOfYear;
	}

	public void setWeekOfYear(int weekOfYear) {
		this.weekOfYear = weekOfYear;
	}

	public int getWeekOfMonth() {
		return weekOfMonth;
	}

	public void setWeekOfMonth(int weekOfMonth) {
		this.weekOfMonth = weekOfMonth;
	}

	public boolean isCross() {
		return isCross;
	}

	public void setCross(boolean isCross) {
		this.isCross = isCross;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
