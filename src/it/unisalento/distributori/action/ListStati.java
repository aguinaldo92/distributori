/**
 * 
 */
package it.unisalento.distributori.action;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;


/**
 * @author aguinaldo
 *
 */
public class ListStati extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2053438643831871071L;
	private HashMap<Integer,String> stati = new HashMap<>();
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	public String execute(){
		try{
			logger.debug("execute()");
			stati.put(0, "Guasto");
			stati.put(1, "Rifornimento atteso");
			stati.put(2, "OK");
			ServletActionContext.getRequest().setAttribute("stati", stati);
			return SUCCESS;
		} catch (Exception e){
			logger.error("Impossibile caricare l'elenco degli stati possibili per un qualsiasi distributore",e);
			addActionError("Impossibile caricare l'elenco degli stati possibili per un qualsiasi distributore");
			return ERROR;
		}
	}








}
