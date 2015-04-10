package com.excilys.cdb.dao;

public abstract class Dao<T> {

	public abstract T create(T obj);

	public abstract T find(long id);

	public abstract T update(T obj);

	public abstract void delete(T obj);
}
