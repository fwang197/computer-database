package com.excilys.cdb.clientService;

import java.util.List;

import com.excilys.cdb.model.Computer;

public interface IClientComputerService {
	public List<Computer> getAll();

	public Computer getComputer(long id);

	public void create(Computer computer);

	public void update(Computer computer);

	public void delete(long id);

}
