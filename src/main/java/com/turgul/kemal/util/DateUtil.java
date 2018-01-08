package com.turgul.kemal.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author kemalturgul
 * @date Jan 5, 2018
 */
public class DateUtil {

	public static Date dateAddDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}

	public static Date dateRemoveDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -days);
		return cal.getTime();
	}

	public static Date dateAddOneDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	public static Date dateAddHours(Date date, int hours) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, hours);
		return cal.getTime();
	}

	public static Date dateRemoveHours(Date date, int hours) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, -hours);
		return cal.getTime();
	}

	public static String getFormattedDate(Date date, String pattern) {
		String dateConverted = null;
		if (date != null && pattern != null && pattern.trim().length() > 0) {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			dateConverted = formatter.format(date);
		}

		return dateConverted;
	}

	public static Date getFormattedDate(String dateString, String pattern) throws ParseException {
		Date parsedDate = null;
		if (dateString != null && pattern != null && pattern.trim().length() > 0) {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			parsedDate = formatter.parse(dateString);
		}

		return parsedDate;
	}

}
