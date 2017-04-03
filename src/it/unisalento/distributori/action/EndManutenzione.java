package it.unisalento.distributori.action;

import java.util.Date;
import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Manutiene;
import it.unisalento.distributori.factory.FactoryDao;

public class EndManutenzione extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8303075637005903513L;
	private int idDistributore;

	public String execute() throws Exception{
		/*
		 * le richieste di manutenzione possono presentarsi una alla volta poichè includono 
		 * qualsiasi tipo di problema e malfunzionamento. Pertanto, un distributore può avere al più
		 * una manutenzione pendente per volta. Inoltre, si ipotizza che a terminare la manutenzione
		 * sia lo stesso dipendente che l'ha iniziata.
		*/
		Manutiene manutenzione = FactoryDao.getIstance().getManutieneDao().getManutenzionePendenteByDistributore(idDistributore);
		manutenzione.setDataFine(new Date());
		FactoryDao.getIstance().getManutieneDao().update(manutenzione);
		
		return SUCCESS;
	}

	public int getIdDistributore() {
		return idDistributore;
	}

	public void setIdDistributore(int idDistributore) {
		this.idDistributore = idDistributore;
	}
	
}
