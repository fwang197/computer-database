package com.excilys.cdb.dao;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Dao.
 *
 * @param <T>
 *            the generic type
 */
public interface Dao<T> {

	/**
	 * Permet de créer une entrée dans la base de données par rapport à un
	 * objet.
	 *
	 * @param obj
	 *            the obj
	 */
	public void create(T obj);

	/**
	 * Permet de retrouver un objet via son ID.
	 *
	 * @param id
	 *            the id
	 * @return obj
	 */
	public T find(long id);

	/**
	 * Permet de mettre à jour les données d'une entrée dans la base.
	 *
	 * @param obj
	 *            the obj
	 */
	public void update(T obj);

	/**
	 * Permet de supprimer une entrée de la base.
	 *
	 * @param obj
	 *            the obj
	 */
	public void delete(T obj);

	/**
	 * Permet de recuperer toute les entrées de la base dans une liste d'objet.
	 *
	 * @return the linked list
	 */
	public List<T> findAll();
}
