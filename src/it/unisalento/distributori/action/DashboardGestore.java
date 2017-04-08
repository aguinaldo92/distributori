/**
 * 
 */
package it.unisalento.distributori.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.factory.FactoryDao;

/**
 * @author aguinaldo
 *
 */
public class DashboardGestore extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1185507316547998950L;
	private Long numMessaggiNonLetti;
	private Long numDistributoriNonOk;
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	public String execute(){
		try{
			logger.debug("execute()");
			numMessaggiNonLetti = FactoryDao.getIstance().getFeedbackDao().getNumMessaggiNonLetti();
			numDistributoriNonOk = FactoryDao.getIstance().getDistributoreDao().getNumDistributoriNonOk();

			ServletActionContext.getRequest().setAttribute("numMessaggiNonLetti", numMessaggiNonLetti);
			ServletActionContext.getRequest().setAttribute("numDistributoriNonOk", numDistributoriNonOk);
			return SUCCESS;
			
		} catch (Exception e){
			logger.error("Impossibile visualizzare il numero di messaggi non letti e di distributori che necessitano di un intervento nella dashboard del gestore ",e);
			return ERROR;
		}
	}


}