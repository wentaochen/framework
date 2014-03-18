package com.framework.infrastructure.utils.time;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Period;
import org.junit.Assert;
import org.junit.Test;

public class JdoaTimeTest extends Assert {

	@Test
	public void test() {
		LocalDate now = new LocalDate();
		DateTime time = new DateTime();
		System.out.println(time.dayOfWeek().withMinimumValue());
		System.out.println(time.dayOfWeek().withMaximumValue());
		System.out.println(time.minusWeeks(1).dayOfWeek().getAsString());

		System.out.println(time.plusDays(7));
		System.out.println(time);
		Days.daysBetween(new DateTime(), new DateTime());
		Months.monthsBetween(new DateTime(), new DateTime());
		Hours.hoursBetween(new DateTime(), new DateTime());

		Period period = new Period(time, time.plusDays(7));
		// period.
		/*
		 * Period - An immutable implementation MutablePeriod - A mutable
		 * implementation Years - An immutable years-only implementation Months
		 * - An immutable months-only implementation Weeks - An immutable
		 * weeks-only implementation Days - An immutable days-only
		 * implementation Hours - An immutable hours-only implementation Minutes
		 * - An immutable minutes-only implementation Seconds - An immutable
		 * seconds-only implementation
		 */
	}
}
