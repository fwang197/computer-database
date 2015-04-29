package com.excilys.cdb.dao;

import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.exception.NotImplemented;
import com.excilys.cdb.model.Company;

public interface IDao<T> {
	/**
	 * Creates the T.
	 *
	 * @param obj
	 * @return the id
	 */
	public default long create(T obj) {
		throw new NotImplemented();
	}

	/**
	 * Find a T according to its id.
	 *
	 * @param id
	 * @return the computer
	 */
	public default T find(long id) {
		throw new NotImplemented();
	}

	/**
	 * Update.
	 *
	 * @param obj
	 */
	public default void update(T obj) {
		throw new NotImplemented();
	}

	/**
	 * Delete.
	 *
	 * @param obj
	 */
	public default void delete(T obj) {
		throw new NotImplemented();
	}

	/**
	 * Delete without connection.
	 *
	 * @param obj
	 * @throws SQLException
	 */
	public default void deleteWithoutConnection(T obj) throws SQLException {
		throw new NotImplemented();
	}

	/**
	 * Gets the count of T.
	 *
	 * @return the count
	 */
	public default int getCount() {
		throw new NotImplemented();
	}

	/**
	 * Gets the count of T according to a pattern in the companies or computers
	 * name.
	 *
	 * @param pattern
	 * @return the count
	 */
	public default int getCount(String pattern) {
		throw new NotImplemented();
	}

	/**
	 * Find all T.
	 *
	 * @return the list
	 */
	public default List<T> findAll() {
		throw new NotImplemented();
	}

	/**
	 * Find a range of T order by column name and its way (ASC or DESC).
	 *
	 * @param offset
	 * @param range
	 * @param by
	 * @param order
	 * @return the list
	 */
	public default List<T> findAll(int offset, int range, String by,
			String order) {
		throw new NotImplemented();
	}

	/**
	 * Find a range of T according to a pattern and order by column name and its
	 * way (ASC or DESC).
	 *
	 * @param offset
	 * @param range
	 * @param pattern
	 * @param by
	 * @param order
	 * @return the list
	 */
	public default List<T> findAll(int offset, int range, String pattern,
			String by, String order) {
		throw new NotImplemented();
	}

	/**
	 * Find all the T who belong to a company.
	 *
	 * @param obj
	 *            the obj
	 * @return the list
	 */
	public default List<T> findAll(Company obj) {
		throw new NotImplemented();
	}
}