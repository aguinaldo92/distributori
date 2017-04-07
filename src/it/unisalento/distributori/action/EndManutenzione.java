package it.unisalento.distributori.action;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.domain.Manutiene;
import it.unisalento.distributori.factory.FactoryDao;

public class EndManutenzione extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4114392520497262965L;
	private int idDistributore;
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	public String execute(){
		/*
		 * le richieste di manutenzione possono presentarsi una alla volta poichè includono 
		 * qualsiasi tipo di problema e malfunzionamento. Pertanto, un distributore può avere al più
		 * una manutenzione pendente per volta. Inoltre, si ipotizza che a terminare la manutenzione
		 * sia lo stesso dipendente che l'ha iniziata.
		 */
		try{
			logger.trace("execute()");
			Manutiene manutenzione = FactoryDao.getIstance().getManutieneDao().getManutenzionePendenteByDistributore(idDistributore);
			manutenzione.setDataFine(new Date());
			FactoryDao.getIstance().getManutieneDao().update(manutenzione);

			Distributore distributore=FactoryDao.getIstance().getDistributoreDao().get(idDistributore, Distributore.class);
			distributore.setStato(FactoryDao.getIstance().getDistributoreDao().getUpdatedStato(idDistributore));
			FactoryDao.getIstance().getDistributoreDao().update(distributore);

			return SUCCESS;

		}catch (NullPointerException ne){
			logger.error("Impossibile visualizzare le manutenzioni del distributore scelto",ne);
			addActionError("Impossibile visualizzare le manutenzioni del distributore scelto");
			return ERROR;
		} catch (Exception e){
			logger.error("Impossibile modificare lo stato del distributore con id: "+idDistributore,e);
			addActionError("Impossibile modificare lo stato del distributore con id: "+idDistributore);
			return ERROR;
		}
	}

	public int getIdDistributore() {
		return idDistributore;
	}
	public void setIdDistributore(int idDistributore) {
		this.idDistributore = idDistributore;
	}

}
