package com.excilys.cdb.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exception.DaoException;

// TODO: Auto-generated Javadoc
/**
 * The Class Tools.
 */
public class Tools {

	private final static Logger logger = LoggerFactory.getLogger(Tools.class);

	/**
	 * Checks if the string is a number.
	 *
	 * @param num
	 * @return true, if its number
	 */
	public static boolean isNumber(String num) {
		if (num == null)
			return false;
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(num);
		return m.matches();
	}

	/**
	 * Checks if an object is null.
	 *
	 * @param o
	 * @return true, if its null
	 */
	public static boolean isNull(Object o) {
		return o == null;
	}

	/**
	 * Close properly the ResultSet and the PreparedStatement.
	 *
	 * @param rs
	 * @param pstat
	 */
	public static void closeProperly(ResultSet rs, Statement stat) {
		try {
			if (!Tools.isNull(rs)) {
				rs.close();
			}
			if (!Tools.isNull(stat)) {
				stat.close();
			}
		} catch (SQLException e) {
			logger.error("Tools : can't close properly");
			throw new DaoException();
		}

	}

	/**
	 * Execute a script who rebuild the test database.
	 */
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
			logger.error("Tools : process error");
		}
	}
}
