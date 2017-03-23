/**
 * 
 */
package it.unisalento.distributori.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.factory.FactoryDao;

/**
 * @author aguinaldo
 *
 */
public class ListStati extends ActionSupport{


	private static final long serialVersionUID = 9148761638899536748L;
	private List<String> stati = new ArrayList<>();

	public String execute(){
		try{
			stati.add(0, "Guasto");
			stati.add(1, "Rifornimento atteso");
			stati.add(2, "OK");
			ServletActionContext.getRequest().setAttribute("stati", stati);
			System.out.println("STATI!");
			return SUCCESS;
		}catch(Exception e){
			return ERROR;
		}
	}


	
	

}
