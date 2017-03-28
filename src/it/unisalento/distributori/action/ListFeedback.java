package it.unisalento.distributori.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Feedback;
import it.unisalento.distributori.factory.FactoryDao;

public class ListFeedback  extends ActionSupport{
	
	private static final long serialVersionUID = -5293348280465542564L;
	
	private List<Feedback> feedbacks = new ArrayList<Feedback>();
	private Long numFeedbackNonLetti;
	
	public String execute() {
		
		feedbacks = FactoryDao.getIstance().getFeedbackDao().getAllSortedBy(Feedback.class, "data desc");
		numFeedbackNonLetti=FactoryDao.getIstance().getFeedbackDao().getNumMessaggiNonLetti();
		System.out.println("Numero feedbacks estratti: "+feedbacks.size());
		
		ServletActionContext.getRequest().setAttribute("feedbacks", feedbacks);
		ServletActionContext.getRequest().setAttribute("numFeedbackNonLetti", numFeedbackNonLetti);
		
		return SUCCESS;
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
