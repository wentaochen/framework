package com.framework.infrastructure.utils.time;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class WeekTest extends Assert {

	@Test
	public void testGetDaysOfWeek() {
		List<Day> days = Week.getDays(new Date());
		SimpleDateFormat format = new SimpleDateFormat(
				FormatterConstants.FROMAT_WHIT_BACK);
		for (Day day : days) {
			System.out.println(format.format(day.getTime()));
		}
	}

}
