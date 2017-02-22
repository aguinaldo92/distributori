/**
 * 
 */
package it.unisalento.distributori.action;

import java.util.HashMap;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author aguinaldo
 *
 */
public class DettaglioDistributore extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3181980036211349123L;
	private Integer idDistributore;
	private Integer scaffali = 10;
	private Integer posti = 5;
	
		
	public Integer getIdDistributore() {
		return idDistributore;
	}
	public void setIdDistributore(Integer idDistributore) {
		this.idDistributore = idDistributore;
	}
	public Integer getScaffali() {
		return scaffali;
	}
	public void setScaffali(Integer scaffali) {
		this.scaffali = scaffali;
	}
	public Integer getPosti() {
		return posti;
	}
	public void setPosti(Integer posti) {
		this.posti = posti;
	}

	// mi servono scaffale e posto, quantità , nome prodotto
	public String execute(){
		
		
		
		return SUCCESS;
		
		
	}

}
