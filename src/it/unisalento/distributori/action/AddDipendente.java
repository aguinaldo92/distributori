package it.unisalento.distributori.action;

import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.PersonaModel;
import it.unisalento.distributori.util.GeneraPwd;
import it.unisalento.distributori.util.SendMailSSL;

/***
 * 
 * Per sfruttare il meccaniscmo di validazione all'interno della Action
 * è necessario estendere la classe con ActionSupport (ovviamente dovete fare l'import!!!!)
 *
 *	Per sfuttare l'interceptor ModelDriven la action deve implementare l'interfaccia ModelDriven
 */
public class AddDipendente extends ActionSupport implements ModelDriven<PersonaModel>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2120311945916315868L;
	private PersonaModel DipForm = new PersonaModel();
	private Dipendente new_Dip = new Dipendente();
	private Persona new_persona = new Persona();

	public Persona getNew_persona() {
		return new_persona;
	}

	public void setNew_persona(Persona new_persona) {
		this.new_persona = new_persona;
	}

	public Dipendente getNew_Dip() {
		return new_Dip;
	}

	public void setNew_Dip(Dipendente new_Dip) {
		this.new_Dip = new_Dip;
	}

	public String execute() {

		int new_id=-1;
		int dim_pw=6;
		GeneraPwd pw_generator = new GeneraPwd(dim_pw);//generatore di password lunghe 6 caratteri

		System.out.println("Sono entrato nella action AddDipendente - ID dipendente="+DipForm.getId());

		new_persona.setCognome(DipForm.getCognome());//modifico con quelli del form
		new_persona.setNome(DipForm.getNome());
		new_persona.setEmail(DipForm.getEmail());
		new_persona.setPassword(pw_generator.getPWD());
		new_persona.setRuolo(1);//ruolo 1 = dipendente
		new_Dip.setTelefono(DipForm.getTelefono());
		new_persona.setDipendente(new_Dip);

		//inserisco persona e dipendente nel DB

		new_persona.setId(FactoryDao.getIstance().getPersonaDao().set(new_persona));
		new_Dip.setPersona(new_persona);
		FactoryDao.getIstance().getDipendenteDao().set(new_Dip);


		SendMailSSL sending_mail=new SendMailSSL();
		sending_mail.send(new_persona.getEmail(), "WiFi Drink&Snacks - NOTIFICA DI INSERIMENTO NEL GESTIONALE", "Gent.mo/a Sig./Sig.ra "+
				new_persona.getNome()+" "+new_persona.getCognome()+
				", la presente per notificarLe l'avvenuta registrazione nel gestionale aziendale "+
				"del suo nominativo. Da questo momento potrà accedervi con le seguenti credenziali: username: "+
				new_persona.getEmail()+" - password: "+new_persona.getPassword()+" ("+dim_pw+" caratteri)."+
				" Buon lavoro!!!"+
				" Cordialmente. Giovanni Rana");

		System.out.println("Inserito il dipendente nel DB. ID USER="+new_id);

		return SUCCESS;
	}

	public void validate(){
		if(DipForm.getEmail().length()>0){
			if (FactoryDao.getIstance().getPersonaDao().emailExists(DipForm.getEmail(), null)){
				System.out.println("La mail è già presente");
				addFieldError("email_esistente", "Email già presente");
			}
		}
	}

	@Override
	public PersonaModel getModel() {
		// TODO Auto-generated method stub
		return DipForm;
	}

}