package com.excilys.cdb.clientService;

import java.util.List;

import com.excilys.cdb.model.Computer;

/**
 * The Interface IClientComputerService.Collect the data from web-service
 * computer.
 */
public interface IClientComputerService {

	/**
	 * Gets all the computers.
	 *
	 * @return List of all the computers.
	 */
	public List<Computer> getAll();

	/**
	 * Gets the computer by his id.
	 *
	 * @param id
	 *            the id
	 * @return the computer
	 */
	public Computer getComputer(long id);

	/**
	 * Create.
	 *
	 * @param computer
	 *            the computer
	 */
	public void create(Computer computer);

	/**
	 * Update.
	 *
	 * @param computer
	 *            the computer
	 */
	public void update(Computer computer);

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 */
	public void delete(long id);

}
