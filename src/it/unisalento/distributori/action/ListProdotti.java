package it.unisalento.distributori.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.domain.Famiglia;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.factory.FactoryDao;

public class ListProdotti extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6710858723062171092L;
	private List<String> list_categorie_scelte = new ArrayList<String>();
	private List<String> list_famiglie_scelte = new ArrayList<String>();
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
		
		this.categorie=FactoryDao.getIstance().getCategoriaDao().getAllCategorie();
		this.famiglie=FactoryDao.getIstance().getFamigliaDao().getAllSortedBy(Famiglia.class, "nome");
		
		//ottenimento della lista dei prodotti in base alle condizioni di filtraggio
		if(list_categorie_scelte!=null && list_famiglie_scelte!=null && (list_categorie_scelte.size()>0 || list_famiglie_scelte.size()>0)){
			this.prodotti=FactoryDao.getIstance().getProdottoDao().getAllProdottiFiltrati(list_famiglie_scelte, list_categorie_scelte);
		}else{
			this.prodotti=FactoryDao.getIstance().getProdottoDao().getAllProdotti();
		}

		ServletActionContext.getRequest().setAttribute("prodotti", prodotti);
		ServletActionContext.getRequest().setAttribute("categorie", categorie);
		ServletActionContext.getRequest().setAttribute("categorie_scelte", list_categorie_scelte);
		ServletActionContext.getRequest().setAttribute("famiglie", famiglie);
		ServletActionContext.getRequest().setAttribute("famiglie_scelte", list_famiglie_scelte);
		
		System.out.println("Caricato il catalogo. Filtraggio categorie: "+list_categorie_scelte+", filtraggio famiglie: "+list_famiglie_scelte+", N° prodotti: "+prodotti.size());
		
		return SUCCESS;
	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}

	public List<String> getList_categorie_scelte() {
		return list_categorie_scelte;
	}

	public void setList_categorie_scelte(List<String> list_categorie_scelte) {
		this.list_categorie_scelte = list_categorie_scelte;
	}

	public List<String> getList_famiglie_scelte() {
		return list_famiglie_scelte;
	}

	public void setList_famiglie_scelte(List<String> list_famiglie_scelte) {
		this.list_famiglie_scelte = list_famiglie_scelte;
	}

}