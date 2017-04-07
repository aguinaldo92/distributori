package it.unisalento.distributori.action;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import it.unisalento.distributori.domain.Feedback;
import it.unisalento.distributori.factory.FactoryDao;

public class ListFeedback  extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7360950940612034526L;
	private List<Feedback> feedbacks = new ArrayList<Feedback>();
	private Long numFeedbackNonLetti;
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	public String execute() {
		try{
		logger.trace("execute()");
		feedbacks = FactoryDao.getIstance().getFeedbackDao().getAllSortedBy(Feedback.class, "data desc");
		numFeedbackNonLetti = FactoryDao.getIstance().getFeedbackDao().getNumMessaggiNonLetti();
		ServletActionContext.getRequest().setAttribute("feedbacks", feedbacks);
		ServletActionContext.getRequest().setAttribute("numFeedbackNonLetti", numFeedbackNonLetti);
		
		return SUCCESS;
		
		}catch (Exception e){
			logger.error("Impossibile caricare l'elenco dei Distributori da parte del gestore",e);
			return ERROR;
		}
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}
	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}
	public Long getNumFeedbackNonLetti() {
		return numFeedbackNonLetti;
	}
	public void setNumFeedbackNonLetti(Long numFeedbackNonLetti) {
		this.numFeedbackNonLetti = numFeedbackNonLetti;
	}
}
