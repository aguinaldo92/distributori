/**
 * 
 */
package it.unisalento.distributori.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.factory.FactoryDao;

/**
 * @author aguinaldo
 *
 */
public class DashboardGestore extends ActionSupport {
	private static final long serialVersionUID = 8465098953853422486L;
	private Long numMessaggiNonLetti;
	private Long numDistributoriNonOk;
	
	public String execute(){
		numMessaggiNonLetti = FactoryDao.getIstance().getFeedbackDao().getNumMessaggiNonLetti();
		numDistributoriNonOk = FactoryDao.getIstance().getDistributoreDao().getNumDistributoriNonOk();
		
		ServletActionContext.getRequest().setAttribute("numMessaggiNonLetti", numMessaggiNonLetti);
		ServletActionContext.getRequest().setAttribute("numDistributoriNonOk", numDistributoriNonOk);
		
		return SUCCESS;
	}
	

}
