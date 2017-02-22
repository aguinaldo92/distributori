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
	private Integer id_distributore;
	private Integer scaffali = 10;
	private Integer posti = 5;
	
	
	
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

	
	public Integer getId_distributore() {
		return id_distributore;
	}
	public void setId_distributore(Integer id_distributore) {
		this.id_distributore = id_distributore;
	}
	// mi servono scaffale e posto, quantità , nome prodotto
	public String execute(){
		
		
		
		return SUCCESS;
		
		
	}

}
