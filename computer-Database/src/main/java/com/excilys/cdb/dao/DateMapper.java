package com.excilys.cdb.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.excilys.cdb.tools.Tools;

public class DateMapper {

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

	public static LocalDateTime toDateFormat(String date) {
		LocalDateTime ldt = null;
		Pattern p = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
		Matcher m = p.matcher(date);
		if (m.matches()) {
			if (Tools.checkGoodDate(date)) {
				ldt = DateMapper.toLocalDateTime(java.sql.Timestamp
						.valueOf(date + " 00:00:00"));
			}
		}
		return ldt;
	}
}
