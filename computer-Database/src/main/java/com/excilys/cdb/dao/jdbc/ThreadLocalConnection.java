package com.excilys.cdb.dao.jdbc;

import java.sql.Connection;

public class ThreadLocalConnection {
	public static final ThreadLocal userThreadLocal = new ThreadLocal();

	public static void set(Connection conn) {
		userThreadLocal.set(conn);
	}

	public static Connection get() {
		return (Connection) userThreadLocal.get();
	}
}
