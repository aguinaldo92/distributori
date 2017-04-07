package it.unisalento.distributori.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Feedback;
import it.unisalento.distributori.factory.FactoryDao;

public class UpdateFeedback  extends ActionSupport{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3173911430524122515L;
	private int id;
	private String submit_btn;
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	public String execute() {
		try{
			logger.trace("execute()");
			Feedback feedback = FactoryDao.getIstance().getFeedbackDao().get(id, Feedback.class);

			Byte letto=0;
			if(submit_btn.compareTo("Segna come letto")==0){
				letto=1;
			}
			feedback.setLetto(letto);
			FactoryDao.getIstance().getFeedbackDao().update(feedback);
			return SUCCESS;
			
		} catch (Exception e){
			logger.error("Impossibile impostare il messaggio con ID: "+ id,e);
			addActionError("Impossibile segnare il messaggio come letto, riprovare");
			return ERROR;
		}
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubmit_btn() {
		return submit_btn;
	}
	public void setSubmit_btn(String submit_btn) {
		this.submit_btn = submit_btn;
	}

}
