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
 * ��ʾ����,������һ������
 * 
 * @author chenwentao
 *
 * @version 0.9
 *
 * �޸İ汾: 0.9
 * �޸�����: Feb 26, 2011
 * �޸��� :  chenwentao
 * �޸�˵��: �������
 * ������ ��
 * </pre>
 */
public class Week {

	protected String monday;

	protected String sunday;

	protected int weekOfYear;

	protected int weekOfMonth;

	/**
	 * �ж�һ�������Ƿ�Խ��
	 */
	protected boolean isCross;

	/**
	 * ���ĳһ���ܵ���������
	 * 
	 * @param date
	 * @return
	 */
	public static List<Day> getDays(Date date) {
		DateTime time = new DateTime(date.getTime());
		Long value = time.dayOfWeek().withMinimumValue().toDate().getTime();
		DateTime fristDay = new DateTime(value);
		List<Day> result = new ArrayList<Day>();
		// ������һ����
		result.add(new Day(fristDay.toDate(),
				FormatterConstants.FROMAT_ONLY_DAY));
		// ����ʣ���6��
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
	 * ���ݵ�ǰ����������,��ʼ������һ��������,ͬʱ��ʼ���ǵڼ���
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