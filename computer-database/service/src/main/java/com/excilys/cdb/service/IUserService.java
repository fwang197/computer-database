package com.excilys.cdb.service;

import com.excilys.cdb.model.User;

/**
 * The Interface IUserService.
 */
public interface IUserService {

	/**
	 * Find by user name.
	 *
	 * @param username
	 *            the username
	 * @return the user
	 */
	public User findByUserName(String username);
}
