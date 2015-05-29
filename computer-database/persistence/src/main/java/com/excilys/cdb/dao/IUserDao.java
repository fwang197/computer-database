package com.excilys.cdb.dao;

import com.excilys.cdb.model.User;

public interface IUserDao {

	/**
	 * Find by user name.
	 *
	 * @param username
	 *            the username
	 * @return the user
	 */
	public User findByUserName(String username);
}
