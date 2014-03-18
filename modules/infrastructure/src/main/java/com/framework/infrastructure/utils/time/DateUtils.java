package com.framework.infrastructure.utils.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.ocpsoft.pretty.time.PrettyTime;

/**
 * <pre>
 * @author chenwentao
 *
 * @version 0.9
 *
 * 修改版本: 0.9
 * 修改日期: Feb 17, 2011
 * 修改人 :  chenwentao
 * 修改说明: 初步完成
 * 复审人 ：
 * </pre>
 */
public abstract class DateUtils {

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * 获得N天以后的日期(包含当天)
	 * 
	 * @param now
	 * @param number
	 * @param dateFormate
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getAfterDays(String now, int number,
			String dateFormate) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat(dateFormate);
		calendar.setTime(format.parse(now));

		List<String> results = new ArrayList<String>();
		// 包含查询的时间
		results.add(now);
		for (int i = 1; i < number; i++) {
			calendar.set(Calendar.DAY_OF_MONTH, calendar
					.get(Calendar.DAY_OF_MONTH)
					+ i);

			results.add(format.format(calendar.getTime()));
			calendar.setTime(format.parse(now));
		}

		return results;
	}

	/**
	 * 获得N天之前的日期(包含当天)
	 * 
	 * @param now
	 * @param number
	 * @param dateFormate
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getBeforeDays(String now, int number,
			String dateFormate) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat(dateFormate);
		calendar.setTime(format.parse(now));
		List<String> results = new ArrayList<String>();
		// 包含查询的时间
		results.add(now);
		for (int i = 1; i < number; i++) {
			calendar.set(Calendar.DAY_OF_MONTH, calendar
					.get(Calendar.DAY_OF_MONTH)
					- i);

			results.add(format.format(calendar.getTime()));
			calendar.setTime(format.parse(now));
		}

		return results;

	}

	/**
	 * 获得N天之前的日期(包含当天)
	 * 
	 * @param now
	 * @param number
	 * @param dateFormate
	 * @return
	 * @throws ParseException
	 */
	public static String getBeforeDay(String now, int number, String dateFormate)
			throws ParseException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat(dateFormate);
		calendar.setTime(format.parse(now));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)
				- number);

		return format.format(calendar.getTime());
	}

	public static String addDate(String day, int x) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date date = null;
		try {
			date = format.parse(day);
		}
		catch (ParseException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		if (date == null)
			return "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, x);
		date = cal.getTime();
		System.out.println("3 days after(or before) is " + format.format(date));
		cal = null;
		return format.format(date);
	}

	/**
	 * 取得两个时间之间日期
	 * 
	 * @return
	 */
	public static List<String> getBetweenDate(String begin_date,
			String end_date, String dateFormate) {
		int days_b = getDaysBetweenDates(begin_date, end_date, dateFormate);
		List<String> result = new ArrayList<String>();
		try {
			result = getBeforeDays(end_date, days_b, dateFormate);
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.add(begin_date);
		return result;
	}

	/**
	 * 取得两个时间所差的天数
	 * 
	 * @return
	 */
	public static int getDaysBetweenDates(String begin_date, String end_date,
			String dateFormate) {
		int result = -1;
		SimpleDateFormat format = new SimpleDateFormat(dateFormate);
		Date begin = null;
		Date end = null;
		try {
			begin = format.parse(begin_date);
			end = format.parse(end_date);
			result = (int) (((end.getTime() - begin.getTime()) / 1000) / (24 * 60 * 60));
		}
		catch (ParseException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
	 * 
	 * @param sdate
	 * @param num
	 * @return
	 */
	public static String getWeek(Date date, String num) {
		// 再转换为时间
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if (num.equals("1")) // 返回星期一所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		else if (num.equals("2")) // 返回星期二所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		else if (num.equals("3")) // 返回星期三所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		else if (num.equals("4")) // 返回星期四所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		else if (num.equals("5")) // 返回星期五所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		else if (num.equals("6")) // 返回星期六所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		else if (num.equals("0")) {// 返回星期日所在的日期
			// **注意这里加了7天**
			c.add(Calendar.DAY_OF_MONTH, 7);
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	public static int getBetweenWeekNumber(String startTime, String endTime,
			String format) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parse(startTime, format));
		int startWeek = cal.get(Calendar.WEEK_OF_YEAR);
		cal.setTime(parse(endTime, format));
		int endWeek = cal.get(Calendar.WEEK_OF_YEAR);

		return endWeek - startWeek;
	}

	/**
	 * 获得包含起止之间月数的集合列表(包含自身)
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<Month> getMonths(String startDate, String endDate) {
		int startMonthOfYear = DateUtils.getMonthOfYear(startDate);
		int endMonthOfYear = DateUtils.getMonthOfYear(endDate);
		int startYearOfYear = DateUtils.getYearOfYear(startDate);
		int endYearOfYear = DateUtils.getYearOfYear(endDate);

		List<Month> results = new ArrayList<Month>();

		// 生成第一月
		Month firstMonth = new Month(startDate);
		results.add(firstMonth);

		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.parse(startDate));

		// 根据间隔月数计算
		int intervalNum = endMonthOfYear - startMonthOfYear
				+ (endYearOfYear - startYearOfYear) * 12;
		for (int i = 0; i < intervalNum; i++) {
			// 月数加1
			cal.add(Calendar.MONTH, 1);
			Date date = cal.getTime();
			Month month = new Month(date);
			results.add(month);
		}

		return results;
	}

	/**
	 * 根据起止时间,获得一共有几周(包含自身)
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static List<Week> getWeeks(String startTime, String endTime)
			throws ParseException {
		int betweenDays = DateUtils.getBetweenWeekNumber(startTime, endTime,
				DateUtils.DEFAULT_DATE_FORMAT);

		List<Week> results = new ArrayList<Week>();
		// 获得第一周
		Week firstWeek = new Week(startTime, DateUtils.DEFAULT_DATE_FORMAT);
		results.add(firstWeek);

		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.parse(startTime));

		for (int i = 1; i <= betweenDays; i++) {
			// 余下的周每次加7天
			cal.setTime(DateUtils.parse(startTime));
			cal.add(Calendar.DAY_OF_MONTH, i * 7);
			String date = DateUtils.format(cal.getTime());
			Week week = new Week(date, DateUtils.DEFAULT_DATE_FORMAT);
			results.add(week);
		}

		return results;
	}

	/**
	 * @param date
	 * @param formate
	 * @return
	 */
	public static Date parse(String date, String formate) {
		SimpleDateFormat sdf = new SimpleDateFormat(formate);
		try {
			return sdf.parse(date);
		}
		catch (ParseException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	/**
	 * @param date
	 * @return
	 */
	public static Date parse(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		try {
			return sdf.parse(date);
		}
		catch (ParseException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	/**
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

		return sdf.format(date);
	}

	/**
	 * @param date
	 * @return
	 */
	public static String format(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.format(date);
	}

	/**
	 * 获得当前时间是在一年中的哪个月
	 * 
	 * @return
	 */
	public static int getMonthOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return (cal.get(Calendar.MONTH) + 1);
	}

	/**
	 * 获得当前时间是在一年中的哪个月
	 * 
	 * @return
	 */
	public static int getMonthOfYear(String strDate) {

		return getMonthOfYear(parse(strDate));
	}

	/**
	 * 获得当前时间的年份
	 * 
	 * @return
	 */
	public static int getYearOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return (cal.get(Calendar.YEAR));
	}

	/**
	 * 获得当前时间的年份
	 * 
	 * @return
	 */
	public static int getYearOfYear(String strDate) {

		return getYearOfYear(parse(strDate));
	}

	/**
	 * pretty format
	 */
	public static String prettyFormat(Date date, Locale local) {
		PrettyTime p = new PrettyTime(local);

		return p.format(date);
	}

	/**
	 * pretty format
	 */
	public static String prettyFormat(Date date) {
		return prettyFormat(date, new Locale("ZH_CN"));
	}

	/**
	 * 计算两个任意时间中间的间隔天数 传进Date对象
	 * 
	 * @param args
	 */
	public static int getIntervalDays(Date startday, Date endday) {
		if (startday.after(endday)) {
			Date cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTime();
		long el = endday.getTime();
		long ei = el - sl;
		return (int) (ei / (1000 * 60 * 60 * 24));
	}

	/**
	 * 改进精确计算相隔天数的方法
	 * 注意：通过上面的方法可以衍生出求任何时间，如要查出邮箱三周之内收到的邮件（得到当前系统时间－再得到三周前时间）用收件的时间去匹配 最好装化成
	 * long去比较 如：1年前日期（注意毫秒的转换） java.util.Date myDate=new java.util.Date(); long
	 * myTime=(myDate.getTime()/1000)-60*60*24*365; myDate.setTime(myTime*1000);
	 * String mDate=formatter.format(myDate);
	 */
	public static int getDaysBetween(Calendar d1, Calendar d2) {
		if (d1.after(d2)) {
			java.util.Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}

	public static int getDaysBetween(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);

		return getDaysBetween(c1, c2);
	}

	public static void main(String[] args) {
		System.out.println(StringUtils.class.getProtectionDomain()
				.getCodeSource().getLocation().getFile());
	}
}
