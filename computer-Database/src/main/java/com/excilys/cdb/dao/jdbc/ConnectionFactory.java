package com.excilys.cdb.dao.jdbc;

import java.sql.Connection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

/**
 * A factory for creating Connection objects.
 */
@Component
public class ConnectionFactory {

	private final Logger logger = LoggerFactory
			.getLogger(ConnectionFactory.class);

	@Autowired
	private DataSource dataSource = null;

	/**
	 * Instantiates a new connection factory.
	 */
	private ConnectionFactory() {
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}
}
