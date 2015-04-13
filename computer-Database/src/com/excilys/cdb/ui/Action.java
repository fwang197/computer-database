package com.excilys.cdb.ui;

public abstract class Action {

	// La description de l'action
	public String description;

	/**
	 * Lance la fonctionnalité de l'action.
	 */
	public abstract void execute();
}
