package com.excilys.cdb.service;

import com.excilys.cdb.model.User;

public interface IServiceUser {
	public User findByUserName(String username);
}
