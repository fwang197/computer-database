package com.excilys.cdb.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	/**
	 * The date has the following format YYYY-MM−DD. The method check its
	 * validity.
	 *
	 * @param date
	 * @return true, if its successful
	 */
	public static boolean isDateValid(String date) {
		if (date == null || date.isEmpty()) {
			return true;
		}
		Pattern p = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
		Matcher m = p.matcher(date);

		if (!m.matches()) {
			return false;
		}

		String[] res = date.split("-");
		int year = Integer.parseInt(res[0]);
		int month = Integer.parseInt(res[1]);
		int day = Integer.parseInt(res[2]);

		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) { // 31 jours
			if (day > 0 && day <= 31)
				return true;
		} else if (month == 4 || month == 6 || month == 9 || month == 1) { // 30
																			// jours
			if (day > 0 && day <= 30)
				return true;
		} else if (month == 2) {
			if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
				if (day > 0 && day <= 29)
					return true;
			} else {
				if (day > 0 && day <= 28)
					return true;
			}
		}
		return false;
	}

}
