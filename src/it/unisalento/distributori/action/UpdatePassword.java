package it.unisalento.distributori.action;

import com.opensymphony.xwork2.ActionSupport;

public class UpdatePassword extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6555023403138012964L;

	public String execute(){
		System.out.println("PAssword Modificata");
		return SUCCESS;
	}

}
