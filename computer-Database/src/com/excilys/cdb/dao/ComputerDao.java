package com.excilys.cdb.dao;

import java.util.List;

import com.excilys.cdb.model.Computer;

public interface ComputerDao {

	public void create(Computer obj);

	public Computer find(long id);

	public void update(Computer obj);

	public void delete(Computer obj);

	public List<Computer> findAll();
}
