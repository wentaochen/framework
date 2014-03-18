package com.framework.infrastructure.utils.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class DayTest extends Assert {

	@Test
	public void dayToHour() throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = "2011-11-11";

		List<Hour> hours = Day.getHours(format.parse(time), "yyyy-MM-dd");
		assertEquals("2011-11-11 00:00:00", hours.get(0).getBegin());
		assertEquals("2011-11-11 00:59:59", hours.get(0).getEnd());

		assertEquals("2011-11-11 01:00:00", hours.get(1).getBegin());
		assertEquals("2011-11-11 01:59:59", hours.get(1).getEnd());

		assertEquals("2011-11-11 23:00:00", hours.get(23).getBegin());
		assertEquals("2011-11-11 23:59:59", hours.get(23).getEnd());

		for (Hour hour : hours) {
			System.out.println(hour.getBegin() + " " + hour.getEnd());
		}
	}

	@Test
	public void moveDay() throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = "2011-11-11";
		Date nextDay = Day.moveDay(format.parse(time), 1);
		assertEquals(format.format(nextDay), "2011-11-12");
		Date preDay = Day.moveDay(format.parse(time), -1);
		assertEquals(format.format(preDay), "2011-11-10");

	}
	
}
