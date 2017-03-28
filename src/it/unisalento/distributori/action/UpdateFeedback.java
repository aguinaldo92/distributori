package it.unisalento.distributori.action;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Feedback;
import it.unisalento.distributori.factory.FactoryDao;

public class UpdateFeedback  extends ActionSupport{
	
	private static final long serialVersionUID = -5293348280465542564L;
	
	private int id;
	private String submit_btn;
	
	public String execute() throws Exception {
		
		Feedback feedback = FactoryDao.getIstance().getFeedbackDao().get(id, Feedback.class);
		
		Byte letto=0;
		if(submit_btn.compareTo("Segna come letto")==0){
			letto=1;
		}
		feedback.setLetto(letto);
		
		FactoryDao.getIstance().getFeedbackDao().update(feedback);
		
		return SUCCESS;
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
