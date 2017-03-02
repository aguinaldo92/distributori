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

	private PersonaModel DipForm = new PersonaModel();
	private Dipendente new_Dip = new Dipendente();
	
	public Dipendente getNew_Dip() {
		return new_Dip;
	}

	public void setNew_Dip(Dipendente new_Dip) {
		this.new_Dip = new_Dip;
	}
//	
//	public PersonaModel getDipForm() {
//		return DipForm;
//	}
//
//	public void setDipForm(PersonaModel dipForm) {
//		DipForm = dipForm;
//	}

	public String execute() {
		
		System.out.println("Sono entrato nella action UpdateDipendente - ID dipendente="+DipForm.getId());
				
		//precarico i dati del dipendente
		try {
			new_Dip=FactoryDao.getIstance().getDipendenteDao().get(DipForm.getId(), Dipendente.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new_Dip.setTelefono(DipForm.getTelefono());//modifico con il dato del form
		
		Persona new_persona = new_Dip.getPersona();//precarico i dati della persona
		new_persona.setCognome(DipForm.getCognome());//modifico con quelli del form
		new_persona.setNome(DipForm.getNome());
		new_persona.setEmail(DipForm.getEmail());

//		new_Dip.setPersona(new_persona);
//		new_persona.setDipendente(new_Dip);
		
		//aggiorno persona e dipendente nel DB
		try{
			FactoryDao.getIstance().getPersonaDao().update(new_persona);
			FactoryDao.getIstance().getDipendenteDao().update(new_Dip);
		}catch (Exception e){
			System.out.println("La mail è già presente");
			addActionError("Email già presente");
			return INPUT;
		}
		System.out.println("Aggiornato il dipendente nel DB. ID USER="+new_Dip.getPersonaId());
		
				
		return SUCCESS;
	}
	
	public void validate(){

		ServletActionContext.getRequest().setAttribute("dipendente", DipForm);
		
		System.out.println("sono in validate() di UpdateDipendente: "+DipForm.getCognome()+" "+DipForm.getNome());
		
		if (FactoryDao.getIstance().getPersonaDao().emailExists(DipForm.getEmail(),DipForm.getId())){
			System.out.println("La mail è già presente");
			addActionError("Email già presente");
		}
	}
	
	@Override
	public PersonaModel getModel() {
		// TODO Auto-generated method stub
		return DipForm;
	}

}