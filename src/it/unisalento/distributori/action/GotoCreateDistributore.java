/**
 * 
 */
package it.unisalento.distributori.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.domain.CategorieFornite;
import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.factory.FactoryDao;

/**
 * @author aguinaldo
 *
 */
public class GotoCreateDistributore extends ActionSupport {

	private static final long serialVersionUID = 1650970836755821838L;
	private List<Dipendente> dipendenti = new ArrayList<Dipendente>();
	private List<Categoria> categorie = new ArrayList<Categoria>();
	private List<Integer> categorieFornite = new ArrayList<Integer>();
	private Integer idDistributore;
	private Distributore distributore;

	public String execute() {

		try {
			if (idDistributore != null) {
				distributore = FactoryDao.getIstance().getDistributoreDao().get(idDistributore, Distributore.class);
				categorieFornite = FactoryDao.getIstance().getCategorieForniteDao().getIdsCategorieForniteByDistributore(idDistributore);
			} 
			categorie = FactoryDao.getIstance().getCategoriaDao().getAllCategorie();
			dipendenti = FactoryDao.getIstance().getDipendenteDao().getAll(Dipendente.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}

	public List<Dipendente> getDipendenti() {
		return dipendenti;
	}

	public void setDipendenti(List<Dipendente> dipendenti) {
		this.dipendenti = dipendenti;
	}

	public List<Categoria> getCategorie() {
		return categorie;
	}

	public void setCategorie(List<Categoria> categorie) {
		this.categorie = categorie;
	}

	public Integer getIdDistributore() {
		return idDistributore;
	}

	public void setIdDistributore(Integer idDistributore) {
		this.idDistributore = idDistributore;
	}

	public Distributore getDistributore() {
		return distributore;
	}

	public void setDistributore(Distributore distributore) {
		this.distributore = distributore;
	}

	public List<Integer> getCategorieFornite() {
		return categorieFornite;
	}

	public void setCategorieFornite(List<Integer> categorieFornite) {
		this.categorieFornite = categorieFornite;
	}


	
	
	

}
