package it.unisalento.distributori.action;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.factory.FactoryDao;

public class SetDistributoreGuasto extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2478815811199367680L;
	private int idDistributore;
	
	public String execute() throws Exception{
		Distributore distributore = FactoryDao.getIstance().getDistributoreDao().get(idDistributore, Distributore.class);
		
		distributore.setStato(0);
		
		FactoryDao.getIstance().getDistributoreDao().update(distributore);

		return SUCCESS;
	}

	public int getIdDistributore() {
		return idDistributore;
	}

	public void setIdDistributore(int idDistributore) {
		this.idDistributore = idDistributore;
	}
	
}
