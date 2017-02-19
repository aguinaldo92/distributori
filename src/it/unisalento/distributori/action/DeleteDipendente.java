package it.unisalento.distributori.action;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.opensymphony.xwork2.ActionSupport;
import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;

public class DeleteDipendente extends ActionSupport{
	
	private int idDip;
	private Dipendente deleted_dipendente = new Dipendente();
	private Persona new_persona = new Persona();
	
	public Dipendente getDeleted_dipendente() {
		return deleted_dipendente;
	}

	public void setDeleted_dipendente(Dipendente deleted_dipendente) {
		this.deleted_dipendente = deleted_dipendente;
	}

	public Persona getNew_persona() {
		return new_persona;
	}

	public void setNew_persona(Persona new_persona) {
		this.new_persona = new_persona;
	}

	public Dipendente getDelited_dipendente() {
		return deleted_dipendente;
	}

	public void setDelited_dipendente(Dipendente delited_dipendente) {
		this.deleted_dipendente = delited_dipendente;
	}

	public int getIdDip() {
		return idDip;
	}

	public void setIdDip(int idDip) {
		this.idDip = idDip;
	}

	public String execute () throws Exception {
		
		System.out.println("ID dipendente da eliminare="+idDip);
		this.new_persona=FactoryDao.getIstance().getPersonaDao().get(idDip, Persona.class);
		this.deleted_dipendente=new_persona.getDipendente();
		
		//aggiornamento ruolo
		new_persona.setRuolo(2);
		
		System.out.println("Nominativo dipendente: "+deleted_dipendente.getPersona().getNome()+" "+deleted_dipendente.getPersona().getCognome());

		FactoryDao.getIstance().getPersonaDao().update(new_persona);
		FactoryDao.getIstance().getDipendenteDao().delete(deleted_dipendente);
		
		System.out.println("Dipendente eliminato con successo");
		
		return SUCCESS;
	}
}