package it.unisalento.distributori.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.PersonaModel;

public class DipendenteDetail extends ActionSupport implements SessionAware{
	
	private int idDip;
	private PersonaModel dipendente = new PersonaModel();
	private SessionMap<String, Object> personaSession ;
	private Dipendente dip;

	public PersonaModel getDipendente() {
		return dipendente;
	}

	public void setDipendente(PersonaModel dipendente) {
		this.dipendente = dipendente;
	}

	public int getIdDip() {
		return idDip;
	}

	public void setIdDip(int idDip) {
		this.idDip = idDip;
	}

	public String execute () throws Exception {
		
		if (((Persona)personaSession.get("persona")).getRuolo() != 0 ){
			// l'amministratore può visualizzare tutti i dipendenti
			this.idDip=((Persona)personaSession.get("persona")).getId();
		} 
		dip=FactoryDao.getIstance().getDipendenteDao().get(idDip, Dipendente.class);
		
		System.out.println("ID dipendente da visualizzare="+idDip);
		
		dipendente.setCognome(dip.getPersona().getCognome());
		dipendente.setNome(dip.getPersona().getNome());
		dipendente.setEmail(dip.getPersona().getEmail());
		dipendente.setId(dip.getPersonaId());
		dipendente.setTelefono(dip.getTelefono());
		
		System.out.println("Dipendente ottenuto: "+dipendente.getNome()+" "+dipendente.getCognome());
		
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.personaSession = (SessionMap<String,Object>)map;
		
	}

}