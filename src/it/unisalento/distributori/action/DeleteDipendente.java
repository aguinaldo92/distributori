package it.unisalento.distributori.action;

import com.opensymphony.xwork2.ActionSupport;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.util.SendMailSSL;

public class DeleteDipendente extends ActionSupport{

	private int idDip;
	private Persona new_persona = new Persona();

	public Persona getNew_persona() {
		return new_persona;
	}

	public void setNew_persona(Persona new_persona) {
		this.new_persona = new_persona;
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

		
		System.out.println("Nominativo dipendente: "+new_persona.getNome()+" "+new_persona.getCognome());

		FactoryDao.getIstance().getPersonaDao().delete(new_persona);
		
		SendMailSSL sending_mail=new SendMailSSL();
		sending_mail.send(new_persona.getEmail(), "WiFi Drink&Snacks - NOTIFICA DI CANCELLAZIONE DAL GESTIONALE", "Gent.mo/a Sig./Sig.ra "+
		new_persona.getNome()+" "+new_persona.getCognome()+
		", la presente per notificarLe l'avvenuta eliminazione dal gestionale aziendale "+
		"del suo nominativo. Se vuole continuare ad acquistare i nostri prodotti dovrà registrarsi dalla nostra APP."+
		" Tanta CARNE!!!"+
		" Cordialmente. Giovanni Rana");
		
		System.out.println("Dipendente eliminato con successo");
		
		return SUCCESS;
	}
}