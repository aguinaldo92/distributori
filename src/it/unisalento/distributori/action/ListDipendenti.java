package it.unisalento.distributori.action;

import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.factory.FactoryDao;

public class ListDipendenti extends ActionSupport{
	
	private List<Dipendente> dipendenti = new ArrayList<Dipendente>();
    
	public String execute () throws Exception {
		
		this.dipendenti=FactoryDao.getIstance().getDipendenteDao().getAll(Dipendente.class);
		
		System.out.println("Caricata la lista dei dipendenti. N° dipendenti: "+dipendenti.size());
		
		return SUCCESS;
	}

	public List<Dipendente> getDipendenti() {
		return dipendenti;
	}

	public void setDipendenti(List<Dipendente> dipendenti) {
		this.dipendenti = dipendenti;
	}

}