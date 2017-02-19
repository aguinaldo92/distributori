package it.unisalento.distributori.action;

import com.opensymphony.xwork2.ActionSupport;
import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;

public class EditDipendente extends ActionSupport{
	
	private int idDip;
	private Dipendente dipendente = new Dipendente();

	public Dipendente getDipendente() {
		return dipendente;
	}

	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}

	public int getIdDip() {
		return idDip;
	}

	public void setIdDip(int idDip) {
		this.idDip = idDip;
	}

	public String execute () throws Exception {
		
		System.out.println("ID dipendente da modificare="+idDip);
		
		dipendente=FactoryDao.getIstance().getDipendenteDao().get(idDip, Dipendente.class);
		
		System.out.println("Dipendente ottenuto: "+dipendente.getPersona().getNome()+" "+dipendente.getPersona().getCognome());
		
		return SUCCESS;
	}
}