package it.unisalento.distributori.action;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.domain.Manutiene;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;

public class StartManutenzione extends ActionSupport implements SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4810546429218061600L;
	private int idDistributore;
	private SessionMap<String, Object> personaSession;

	public String execute() throws Exception{
		
		Persona persona_loggata = (Persona) personaSession.get("persona");
		
		Distributore distributore = FactoryDao.getIstance().getDistributoreDao().get(idDistributore, Distributore.class);
		
		Manutiene manutenzione = new Manutiene();
		manutenzione.setDipendente(FactoryDao.getIstance().getDipendenteDao().get(persona_loggata.getId(), Dipendente.class));
		manutenzione.setDistributore(distributore);
		manutenzione.setDataInizio(new Date());
		manutenzione.setId(FactoryDao.getIstance().getManutieneDao().set(manutenzione));
		
		return SUCCESS;
	}

	public int getIdDistributore() {
		return idDistributore;
	}

	public void setIdDistributore(int idDistributore) {
		this.idDistributore = idDistributore;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.personaSession = (SessionMap<String, Object>) map;
		
	}
	
}
