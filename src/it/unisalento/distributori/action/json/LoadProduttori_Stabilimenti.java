package it.unisalento.distributori.action.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Produttore;
import it.unisalento.distributori.factory.FactoryDao;

public class LoadProduttori_Stabilimenti extends ActionSupport  {

	private List<Produttore> all_produttori = new ArrayList<Produttore>();
	private String produttore;
	
	public String getProduttore() {
		return produttore;
	}

	public void setProduttore(String produttore) {
		this.produttore = produttore;
	}

	public String execute() {
		
		System.out.println("AddCategory.execute()");
		
		all_produttori=FactoryDao.getIstance().getProduttoreDao().getAll(Produttore.class);
		
		
		return SUCCESS;
	}
	
	public String getJSON(){
		return execute();
	}
	
	public List<Produttore> getAll_produttori() {
		return all_produttori;
	}

	public void setAll_produttori(List<Produttore> all_produttori) {
		this.all_produttori = all_produttori;
	}

	
}
