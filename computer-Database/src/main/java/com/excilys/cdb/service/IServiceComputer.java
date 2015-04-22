package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Computer;

public interface IServiceComputer {
	public long createComputer(Computer c);

	public Computer findComputer(long id);

	public void updateComputer(Computer c);

	public void deleteComputer(Computer c);

	public List<Computer> findAllComputer();

	public int getCountComputer();

	public int getCountPatternComputer(String pattern);

	public List<Computer> findAllRangeOrderComputer(int offset, int range,
			String by, String order);

	public List<Computer> findAllRangePatternOrderComputer(int offset,
			int range, String pattern, String by, String order);

}
