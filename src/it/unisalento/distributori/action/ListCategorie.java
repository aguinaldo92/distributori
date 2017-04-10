/**
 * 
 */
package it.unisalento.distributori.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.factory.FactoryDao;

/**
 * @author aguinaldo
 *
 */
public class ListCategorie extends ActionSupport{
	private static final long serialVersionUID = -597580512898872535L;
	private List<Categoria> categorie = new ArrayList<Categoria>();
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	public String execute(){
		try{
			logger.debug("execute()");
			categorie = FactoryDao.getIstance().getCategoriaDao().getAllCategorie();
			ServletActionContext.getRequest().setAttribute("categorie", categorie);
			return SUCCESS;
		}catch(Exception e){
			logger.error("Impossibile ottenere l'elenco con tutte le categorie",e);
			return ERROR;
		}
	}

	public List<Categoria> getCategorie() {
		return categorie;
	}
	public void setCategorie(List<Categoria> categorie) {
		this.categorie = categorie;
	}

}
