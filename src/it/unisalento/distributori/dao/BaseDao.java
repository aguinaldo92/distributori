package it.unisalento.distributori.dao;

import java.util.List;

public interface BaseDao<T> {//questa è l'interfaccia di BaseDaoImpl (applicazione del Pattern Bridge)

	public abstract int set(T entity);

	//clazz serve per capire di che oggetto stiamo parlando
	public abstract T get(int id, @SuppressWarnings("rawtypes") Class clazz) ;

	public abstract List<T> getAll(@SuppressWarnings("rawtypes") Class clazz);
	
	public abstract List<T> getAllSortedBy(@SuppressWarnings("rawtypes") Class clazz, String column);
	
	public void update(T entity);
	
	public void delete(T entity);

}