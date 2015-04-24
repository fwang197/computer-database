package com.excilys.cdb.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Auto-generated Javadoc
/**
 * The Class Tools.
 */
public class Tools {

	/**
	 * Checks if is number.
	 *
	 * @param num
	 *            the num
	 * @return true, if is number
	 */
	public static boolean isNumber(String num) {
		if (num == null)
			return false;
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(num);
		return m.matches();
	}

	/**
	 * Checks if is null.
	 *
	 * @param o
	 *            the o
	 * @return true, if is null
	 */
	public static boolean isNull(Object o) {
		return o == null;
	}

	/**
	 * Close properly.
	 *
	 * @param rs
	 *            the rs
	 * @param pstat
	 *            the pstat
	 */
	public static void closeProperly(ResultSet rs, PreparedStatement pstat) {
		try {
			if (!Tools.isNull(rs)) {
				rs.close();
			}
			if (!Tools.isNull(pstat)) {
				pstat.close();
			}
		} catch (SQLException e) {
			System.err.println("Appeler DAOException");
		}

	}

	public static void process() {
		ProcessBuilder p = new ProcessBuilder("./src/test/resources/script.sh");
		try {
			Process pro = p.start();
			InputStream is = pro.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			while (br.readLine() != null) {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * La date est de format YYYY-MM-DD. La fonction verifie sa validité.
	 * 
	 * @param date
	 * @return
	 */
	public static boolean checkGoodDate(String date) {
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
			if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
				return true;
			else
				return false;
		}
		return false;
	}
}
