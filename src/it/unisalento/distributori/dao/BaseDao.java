package it.unisalento.distributori.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.internal.SQLExceptionTypeDelegate;

public interface BaseDao<T> {//questa è l'interfaccia di BaseDaoImpl (applicazione del Pattern Bridge)

	public abstract int set(T entity) throws SQLException;

	//clazz serve per capire di che oggetto stiamo parlando
	public abstract T get(int id, Class clazz) throws Exception;

	public abstract List<T> getAll(Class clazz);
	
	public void update(T entity) throws Exception;
	
	public void delete(T entity);

}