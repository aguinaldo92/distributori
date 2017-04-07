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

	private static final long serialVersionUID = 872831114601812683L;
	private List<Categoria> categorie = new ArrayList<Categoria>();
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	public String execute(){
		try{
			logger.trace("execute()");
			categorie = FactoryDao.getIstance().getCategoriaDao().getAllCategorie();
			ServletActionContext.getRequest().setAttribute("categorie", categorie);
			return SUCCESS;
		}catch(Exception e){
			logger.error("Impossibile ottenere l'elenco con tutte le categorie",e);
			return ERROR;
		}
	}
	// TODO: controllare se quando si utilizza setAttribute non c'è bisogno di getters e setters

	public List<Categoria> getCategorie() {
		return categorie;
	}
	public void setCategorie(List<Categoria> categorie) {
		this.categorie = categorie;
	}

}
