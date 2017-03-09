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
public class ListCategorie extends ActionSupport{

	private static final long serialVersionUID = 872831114601812683L;
	private List<Categoria> categorie = new ArrayList<Categoria>();

	public String execute(){
		try{
		categorie = FactoryDao.getIstance().getCategoriaDao().getAllCategorie();
		ServletActionContext.getRequest().setAttribute("categorie", categorie);
		return SUCCESS;
		}catch(Exception e){
			return ERROR;
		}
	}

}
