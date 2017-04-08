package it.unisalento.distributori.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.util.SendMailSSL;

public class DeleteDipendente extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3171408093241376006L;
	private int idDip;
	private Persona new_persona = new Persona();
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	public String execute ()  {
		try{
			logger.debug("execute()");	
			this.new_persona = FactoryDao.getIstance().getPersonaDao().get(idDip, Persona.class);
			FactoryDao.getIstance().getPersonaDao().delete(new_persona);
			SendMailSSL sending_mail = new SendMailSSL();
			sending_mail.send(new_persona.getEmail(), "WiFi Drink&Snacks - NOTIFICA DI CANCELLAZIONE DAL GESTIONALE", "Gent.mo/a Sig./Sig.ra "+
					new_persona.getNome()+" "+new_persona.getCognome()+
					", la presente per notificarLe l'avvenuta eliminazione dal gestionale aziendale "+
					"del suo nominativo. Se vuole continuare ad acquistare i nostri prodotti dovrà registrarsi dalla nostra APP."+
					" Cordialmente. Giovanni Rana");
			logger.warn("Eliminato Dipendente", new_persona);
			return SUCCESS;
		} catch (Exception e){
			logger.error("Impossibile registrare il nuovo Distributore a causa di un errore ",e);
			return ERROR;
		}
	}

		public int getIdDip() {
			return idDip;
		}
		public void setIdDip(int idDip) {
			this.idDip = idDip;
		}
	}