package com.excilys.cdb.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class Authority. Its represent the role of a user.
 */
@Entity
@Table(name = "authorities")
public class Authority {

	/** The id. */
	@Id
	private long id;

	/** The user. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username", nullable = false)
	private User user;

	/** The authority. */
	private String authority;

	/**
	 * Instantiates a new authority.
	 */
	public Authority() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the authority.
	 *
	 * @return the authority
	 */
	public String getAuthority() {
		return authority;
	}

	/**
	 * Sets the authority.
	 *
	 * @param authority
	 *            the new authority
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
