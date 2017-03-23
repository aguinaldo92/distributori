package it.unisalento.distributori.dao;

import java.util.List;

import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.model.PersonaModel;

public interface PersonaDao extends BaseDao<Persona>{
	
	public Persona getPersonaByCredentials(String email, String password);
	
	public boolean emailExists(String email, Integer my_ID);
	
}
