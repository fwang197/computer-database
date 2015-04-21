package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Computer;

public interface IServiceComputer {
	public long createComputer(Computer c);

	public Computer findComputer(long id);

	public void updateComputer(Computer c);

	public void deleteComputer(Computer c);

	public List<Computer> findAllComputer();

	public List<Computer> findAllRangeComputer(int offset, int range);

	public int getCountComputer();

	public List<Computer> findAllRangePatternComputer(int offset, int range,
			String pattern);

	public int getCountPatternComputer(String pattern);
}
