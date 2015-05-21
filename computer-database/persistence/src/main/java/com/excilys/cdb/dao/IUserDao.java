package com.excilys.cdb.dao;

import com.excilys.cdb.model.User;

public interface IUserDao {
	public User findByUserName(String username);
}
