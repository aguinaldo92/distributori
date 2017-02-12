package it.unisalento.distributori.dao;

import it.unisalento.distributori.domain.Persona;

public interface UserDao extends BaseDao<Persona>{
	
	public Persona getUserByCredentials(String email, String password);
	
}
