package com.excilys.cdb.dao;

import java.util.LinkedList;

public abstract class Dao<T> {

	/**
	 * Permet de créer une entrée dans la base de données par rapport à un
	 * objet.
	 * 
	 * @param obj
	 */
	public abstract void create(T obj);

	/**
	 * Permet de retrouver un objet via son ID.
	 * 
	 * @param id
	 * @return obj
	 */
	public abstract T find(long id);

	/**
	 * Permet de mettre à jour les données d'une entrée dans la base.
	 * 
	 * @param obj
	 */
	public abstract void update(T obj);

	/**
	 * Permet de supprimer une entrée de la base.
	 * 
	 * @param obj
	 */
	public abstract void delete(T obj);

	/**
	 * Permet de recuperer toute les entrées de la base dans une liste d'objet.
	 * 
	 * @return
	 */
	public abstract LinkedList<T> findAll();
}
