package it.unisalento.distributori.action;

import com.opensymphony.xwork2.ActionSupport;
import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.PersonaModel;

public class EditDipendente extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6387264238919678425L;
	private int idDip;
	Dipendente dip;
	private PersonaModel dipendente = new PersonaModel();

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

		System.out.println("ID dipendente da modificare="+idDip);
		try{
			dip=FactoryDao.getIstance().getDipendenteDao().get(idDip, Dipendente.class);
		} catch (NullPointerException ne) {
			System.out.println("EditDipendente: Errore causato da Dipendente dip=FactoryDao.getIstance().getDipendenteDao().get(idDip, Dipendente.class);");
			System.out.println("idDip = " + idDip);
			System.out.println(ne.getMessage());
		}
		dipendente.setCognome(dip.getPersona().getCognome());
		dipendente.setNome(dip.getPersona().getNome());
		dipendente.setEmail(dip.getPersona().getEmail());
		dipendente.setId(dip.getPersonaId());
		dipendente.setTelefono(dip.getTelefono());

		System.out.println("Dipendente ottenuto: "+dipendente.getNome()+" "+dipendente.getCognome());

		return SUCCESS;
	}
}