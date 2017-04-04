package it.unisalento.distributori.action;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.domain.Manutiene;
import it.unisalento.distributori.domain.ProdottiErogati;
import it.unisalento.distributori.factory.FactoryDao;

public class EndManutenzione extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8303075637005903513L;
	private int idDistributore;
	private int quantitaMinima = 5;

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
		
		Distributore distributore=FactoryDao.getIstance().getDistributoreDao().get(idDistributore, Distributore.class);
		List<ProdottiErogati> listProdottiCarenti = FactoryDao.getIstance().getProdottiErogatiDao().getProdottiScarseggiantiByDistributore(idDistributore, quantitaMinima);
		if(listProdottiCarenti.isEmpty())
			distributore.setStato(2);
		else
			distributore.setStato(1);
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
