package com.excilys.cdb.ui;

/**
 * The Class Action.
 */
public abstract class Action {

	// La description de l'action
	/** The description. */
	public String description;

	/**
	 * Lance la fonctionnalité de l'action.
	 */
	public abstract void execute();
}
