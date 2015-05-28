package com.excilys.cdb.service;

import com.excilys.cdb.model.User;

public interface IUserService {
	public User findByUserName(String username);
}
