package it.unisalento.distributori.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.factory.FactoryDao;

public class ListDipendenti extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8658615932292049509L;
	private List<Dipendente> dipendenti = new ArrayList<Dipendente>();
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	public String execute () {
		try{
			logger.trace("execute()");
			this.dipendenti=FactoryDao.getIstance().getDipendenteDao().getAll(Dipendente.class);
			ServletActionContext.getRequest().setAttribute("dipendenti", dipendenti);
			return SUCCESS;
		}catch(Exception e){
			logger.error("Impossibile ottenere l'elenco di tutti i dipendenti",e);
			return ERROR;
		}
	}

	public List<Dipendente> getDipendenti() {
		return dipendenti;
	}
	public void setDipendenti(List<Dipendente> dipendenti) {
		this.dipendenti = dipendenti;
	}
}