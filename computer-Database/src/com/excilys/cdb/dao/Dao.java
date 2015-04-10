package com.excilys.cdb.dao;

public abstract class Dao<T> {

	public abstract void create(T obj);

	public abstract T find(long id);

	public abstract void update(T obj);

	public abstract void delete(T obj);
}
