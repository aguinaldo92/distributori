/**
 * 
 */
package it.unisalento.distributori.action;

import java.util.HashMap;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;


/**
 * @author aguinaldo
 *
 */
public class ListStati extends ActionSupport{


	private static final long serialVersionUID = 9148761638899536748L;
	private HashMap<Integer,String> stati = new HashMap<>();

	public String execute(){
		try{
			stati.put(0, "Guasto");
			stati.put(1, "Rifornimento atteso");
			stati.put(2, "OK");
			ServletActionContext.getRequest().setAttribute("stati", stati);
			System.out.println("STATI!");
			return SUCCESS;
		}catch(Exception e){
			return ERROR;
		}
	}

	
	


	
	

}
