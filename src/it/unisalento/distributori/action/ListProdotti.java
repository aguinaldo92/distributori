package it.unisalento.distributori.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.domain.Famiglia;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.factory.FactoryDao;

public class ListProdotti extends ActionSupport{
	
	private List<Prodotto> prodotti = new ArrayList<Prodotto>();
	private List<Categoria> categorie = new ArrayList<Categoria>();
	private List<Famiglia> famiglie = new ArrayList<Famiglia>();
    
	public List<Famiglia> getFamiglie() {
		return famiglie;
	}

	public void setFamiglie(List<Famiglia> famiglie) {
		this.famiglie = famiglie;
	}

	public List<Categoria> getCategorie() {
		return categorie;
	}

	public void setCategorie(List<Categoria> categorie) {
		this.categorie = categorie;
	}

	public String execute () {
		
		this.prodotti=FactoryDao.getIstance().getProdottoDao().getAllProdotti();
		this.categorie=FactoryDao.getIstance().getCategoriaDao().getAllCategorie();
		this.famiglie=FactoryDao.getIstance().getFamigliaDao().getAll(Famiglia.class);
		
		ServletActionContext.getRequest().setAttribute("prodotti", prodotti);
		ServletActionContext.getRequest().setAttribute("categorie", categorie);
		ServletActionContext.getRequest().setAttribute("famiglie", famiglie);
		
		System.out.println("Caricato il catalogo. N° prodotti: "+prodotti.size());
		
		return SUCCESS;
	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}

}