package it.unisalento.distributori.dao;

import it.unisalento.distributori.domain.Persona;

public interface PersonaDao extends BaseDao<Persona>{
	
	public Persona getPersonaByCredentials(String email, String password);
	
}
