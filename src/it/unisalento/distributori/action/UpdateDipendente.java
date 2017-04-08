package it.unisalento.distributori.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.PersonaModel;

/***
 * 
 * Per sfruttare il meccaniscmo di validazione all'interno della Action
 * è necessario estendere la classe con ActionSupport (ovviamente dovete fare l'import!!!!)
 *
 *	Per sfuttare l'interceptor ModelDriven la action deve implementare l'interfaccia ModelDriven
 */
public class UpdateDipendente extends ActionSupport implements ModelDriven<PersonaModel>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6541506343043318004L;
	private PersonaModel dipendente = new PersonaModel();
	private Dipendente dip = new Dipendente();
	private Logger logger = LogManager.getLogger(this.getClass().getName());


	public String execute() {
		try {
			logger.debug("execute()");
			dip = FactoryDao.getIstance().getDipendenteDao().get(dipendente.getId(), Dipendente.class);
			dip.setTelefono(dipendente.getTelefono());//modifico con il dato del form
			Persona new_persona = dip.getPersona();//precarico i dati della persona
			new_persona.setCognome(dipendente.getCognome());//modifico con quelli del form
			new_persona.setNome(dipendente.getNome());
			new_persona.setEmail(dipendente.getEmail());

			//aggiorno persona e dip nel DB
			FactoryDao.getIstance().getPersonaDao().update(new_persona);
			FactoryDao.getIstance().getDipendenteDao().update(dip);

			addActionMessage("Modifica completata con successo");
			return SUCCESS;
			
		} catch (Exception e){
			logger.error("Impossibile caricare l'elenco dei Distributori da parte del gestore",e);
			addActionError("Impossibile caricare l'elenco dei Distributori");
			return ERROR;
		}
	}

	public void validate(){
		logger.debug("validate()");
		ServletActionContext.getRequest().setAttribute("dipendente", dipendente);

		if (FactoryDao.getIstance().getPersonaDao().emailExists(dipendente.getEmail(),dipendente.getId())){
			System.out.println("La mail è già presente");
			addFieldError("email", "Email già presente");
		}
		if ( hasFieldErrors()){
			addActionError("Ci sono degli errori nell'aggiornamento delle info");
		}

	}

	@Override
	public PersonaModel getModel() {
		return dipendente;
	}
	
	public Dipendente getDip() {
		return dip;
	}
	public void setDip(Dipendente dip) {
		this.dip = dip;
	}




}