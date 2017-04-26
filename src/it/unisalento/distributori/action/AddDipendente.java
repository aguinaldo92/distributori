package it.unisalento.distributori.action;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.PersonaModel;
import it.unisalento.distributori.util.GeneraPwd;
import it.unisalento.distributori.util.PasswordUtils;
import it.unisalento.distributori.util.SendMailSSL;

/***
 * 
 * Per sfruttare il meccaniscmo di validazione all'interno della Action
 * è necessario estendere la classe con ActionSupport (ovviamente dovete fare l'import!!!!)
 *
 *	Per sfuttare l'interceptor ModelDriven la action deve implementare l'interfaccia ModelDriven
 */
public class AddDipendente extends ActionSupport implements ModelDriven<PersonaModel>{
	private static final long serialVersionUID = -482775121433491172L;
	private PersonaModel DipForm = new PersonaModel();
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	private int dim_pw = 6;
	private int ruoloDipendente = 1;

	public String execute() {
		try {
			logger.debug("execute()");
			Persona new_persona = new Persona();
			new_persona.setCognome(DipForm.getCognome());//modifico con quelli del form
			new_persona.setNome(DipForm.getNome());
			new_persona.setEmail(DipForm.getEmail());
			GeneraPwd pw_generator = new GeneraPwd(dim_pw);//generatore di password lunghe 6 caratteri
			String passwordInChiaro = pw_generator.getPWD();
			String hashedPassword = PasswordUtils.getSha256(passwordInChiaro);
			new_persona.setPassword(hashedPassword);
			new_persona.setRuolo(ruoloDipendente);//ruolo 1 = dipendente
			Dipendente new_Dip = new Dipendente();
			new_Dip.setTelefono(DipForm.getTelefono());
			new_persona.setDipendente(new_Dip);
			new_persona.setId(FactoryDao.getIstance().getPersonaDao().set(new_persona));
			new_Dip.setPersona(new_persona);
			FactoryDao.getIstance().getDipendenteDao().set(new_Dip);

			SendMailSSL sending_mail = new SendMailSSL();
			sending_mail.send(new_persona.getEmail(), "WiFi Drink&Snacks - NOTIFICA DI INSERIMENTO NEL GESTIONALE", "Gent.mo/a Sig./Sig.ra "+
					new_persona.getNome()+" "+new_persona.getCognome()+
					", la presente per notificarLe l'avvenuta registrazione nel gestionale aziendale "+
					"del suo nominativo. Da questo momento potrà accedervi con le seguenti credenziali: username: "+
					new_persona.getEmail()+" - password: "+passwordInChiaro+" ("+dim_pw+" caratteri)."+
					" Buon lavoro!!!"+
					" Cordialmente. Giovanni Rana");
			return SUCCESS;

		} catch ( HibernateException sqle) {
			logger.error("Impossibile registrare il nuovo Dipendente a causa di un errore nel salvataggio sul database",sqle);
			addActionError("Impossibile registrare il nuovo Dipendente a causa di un errore nel salvataggio. Controllare che i campi siano correttamente compilati e riprovare o contattare l'amministratore del sistema");
			return INPUT;
		} catch (Exception e){
			logger.error("Impossibile registrare il nuovo Dipendente a causa di un errore non derivante dal database",e);
			return ERROR;
		}
	}

	public void validate(){
		logger.debug("validate()");
		if(FactoryDao.getIstance().getPersonaDao().emailExists(DipForm.getEmail())){
			addFieldError("email_esistente", "Email già presente");
		}
	}

	public PersonaModel getModel() {
		return DipForm;
	}


}