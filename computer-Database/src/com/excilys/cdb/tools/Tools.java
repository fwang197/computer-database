package com.excilys.cdb.tools;

import java.io.IOException;
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
		ProcessBuilder p = new ProcessBuilder("./script.sh");
		try {
			p.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
