package com.excilys.cdb.ui;

/**
 * Leave the application.
 * 
 * @author excilys
 *
 */
public class LeaveAppAction extends Action {

	/**
	 * Instantiates a new leave app action.
	 *
	 * @param description
	 *            the description
	 */
	public LeaveAppAction(String description) {
		this.description = description;
	}

	@Override
	public void execute() {
		Menu.sc.close();
		System.out.println("Bye!");
		System.exit(0);
	}

}
