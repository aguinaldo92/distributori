package it.unisalento.distributori.dao;

import it.unisalento.distributori.domain.Persona;

public interface PersonaDao extends BaseDao<Persona>{
	
	public Persona getPersonaByCredentials(String email, String password);
	
	public boolean emailExists(String email, Integer my_ID);

	boolean emailExists(String email);
	
}
