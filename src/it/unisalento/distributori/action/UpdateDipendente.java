package it.unisalento.distributori.action;

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
	private static final long serialVersionUID = -686794116834734106L;
	private PersonaModel dipendente = new PersonaModel();
	private Dipendente dip = new Dipendente();


	public String execute() {
		
		System.out.println("Sono entrato nella action UpdateDipendente - ID dip="+dipendente.getId());
				
		//precarico i dati del dip
		try {
			dip=FactoryDao.getIstance().getDipendenteDao().get(dipendente.getId(), Dipendente.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dip.setTelefono(dipendente.getTelefono());//modifico con il dato del form
		
		Persona new_persona = dip.getPersona();//precarico i dati della persona
		new_persona.setCognome(dipendente.getCognome());//modifico con quelli del form
		new_persona.setNome(dipendente.getNome());
		new_persona.setEmail(dipendente.getEmail());

		//aggiorno persona e dip nel DB
		try{
			FactoryDao.getIstance().getPersonaDao().update(new_persona);
			FactoryDao.getIstance().getDipendenteDao().update(dip);
		}catch (Exception e){
			System.out.println("La mail è già presente");
			addActionError("Email già presente");
			return INPUT;
		}
		System.out.println("Aggiornato il dip nel DB. ID USER="+dip.getPersonaId());
		
		addActionMessage("Modifica completata con successo");
		return SUCCESS;
	}
	
	public void validate(){
		Boolean errors = false;
		ServletActionContext.getRequest().setAttribute("dipendente", dipendente);
		
		System.out.println("sono in validate() di UpdateDipendente: "+dipendente.getCognome()+" "+dipendente.getNome());
		
		if (FactoryDao.getIstance().getPersonaDao().emailExists(dipendente.getEmail(),dipendente.getId())){
			System.out.println("La mail è già presente");
			addFieldError("email_esistente", "Email già presente");
			errors = true;
		}
		if (errors || hasFieldErrors()){
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