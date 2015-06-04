package com.excilys.cdb.tools;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * The Class DateMapper.
 */
@Component
public class DateMapper {

	@Autowired
	private MessageSource messageSource;

	/**
	 * To local date time.
	 *
	 * @param ts
	 *            the ts
	 * @return the local date time
	 */
	public static LocalDateTime toLocalDateTime(Timestamp ts) {
		return Tools.isNull(ts) ? null : ts.toLocalDateTime();

	}

	/**
	 * To time stamp.
	 *
	 * @param ldt
	 *            the ldt
	 * @return the timestamp
	 */
	public static Timestamp toTimeStamp(LocalDateTime ldt) {
		return Tools.isNull(ldt) ? null : Timestamp.valueOf(ldt);
	}

	/**
	 * To local date time.
	 *
	 * @param year
	 *            the year
	 * @param month
	 *            the month
	 * @param day
	 *            the day
	 * @return the local date time
	 */
	public static LocalDateTime toDate(String year, String month, String day) {
		LocalDateTime ldt = null;
		ldt = DateMapper.toLocalDateTime(java.sql.Timestamp.valueOf(year + "-"
				+ month + "-" + day + " 00:00:00"));
		return ldt;
	}

	/**
	 * To local date time.
	 *
	 * @param date
	 *            the date with format YYYY-MM-DD or DD-MM-YYYY
	 * @return the local date time
	 */
	public LocalDateTime toDateFormat(String date) {
		// Locale locale = LocaleContextHolder.getLocale();
		// if (date == null || date.isEmpty()) {
		// return null;
		// }
		//
		// DateTimeFormatter formatter =
		// DateTimeFormatter.ofPattern(messageSource
		// .getMessage("date.format", null, locale));
		// return LocalDateTime.parse(date, formatter);
		Locale locale = LocaleContextHolder.getLocale();
		String[] res = date.split("-");
		if (locale.getLanguage().equals("en")) {
			return toDate(res[0], res[1], res[2]);
		} else if (locale.getLanguage().equals("fr")) {
			return toDate(res[2], res[1], res[0]);
		}
		return null;
	}

	/**
	 * To string.
	 *
	 * @param ldt
	 *            the ldt
	 * @return the string
	 */
	public String toString(LocalDateTime ldt) {
		// System.out.println("MESSAGE SOURCE " + messageSource);
		// if (!Tools.isNull(ldt)) {
		// Locale locale = LocaleContextHolder.getLocale();
		// return ldt.format(DateTimeFormatter.ofPattern(messageSource
		// .getMessage("date.format", null, locale)));
		// }
		// return "";
		Locale locale = LocaleContextHolder.getLocale();
		if (locale.getLanguage().equals("en")) {
			return ldt
					.format(DateTimeFormatter.ofPattern("yyyy-MM-dd", locale));
		} else if (locale.getLanguage().equals("fr")) {
			return ldt
					.format(DateTimeFormatter.ofPattern("dd-MM-yyyy", locale));
		}
		return "";
	}
}
