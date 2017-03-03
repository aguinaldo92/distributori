package it.unisalento.distributori.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Famiglia;
import it.unisalento.distributori.domain.FamiglieProdotto;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.domain.Produttore;
import it.unisalento.distributori.domain.Stabilimento;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.PersonaModel;
import it.unisalento.distributori.model.ProdottoModel;

public class AddProdotto extends ActionSupport implements ModelDriven<ProdottoModel>{
	
	private ProdottoModel prodotto_Form = new ProdottoModel();
	private String famiglia_scelta;

	Map select_mapping =new HashMap();
	private List<Categoria> all_categ = new ArrayList<Categoria>();
	private List<Famiglia> famiglie = new ArrayList<Famiglia>();
	
	public String getFamiglia_scelta() {
		return famiglia_scelta;
	}

	public void setFamiglia_scelta(String famiglia_scelta) {
		this.famiglia_scelta = famiglia_scelta;
	}
	
	public List<Categoria> getAll_categ() {
		return all_categ;
	}

	public void setAll_categ(List<Categoria> all_categ) {
		this.all_categ = all_categ;
	}

	public List<Famiglia> getFamiglie() {
		return famiglie;
	}

	public void setFamiglie(List<Famiglia> famiglie) {
		this.famiglie = famiglie;
	}

	public Map getSelect_mapping() {
		return select_mapping;
	}

	public void setSelect_mapping(Map select_mapping) {
		this.select_mapping = select_mapping;
	}

	public String execute(){
		
		System.out.println("AddProdotto: execute()");

		return SUCCESS;
	}

	public String load_info(){
		System.out.println("AddProdotto: load_info()");
		
		//caricamento delle categorie e delle famiglie
		all_categ=FactoryDao.getIstance().getCategoriaDao().getAllCategorie();
		famiglie=FactoryDao.getIstance().getFamigliaDao().getAll(Famiglia.class);
		
		//caricamento delle doubleselect per Produttore e Stabilimento
		List<Produttore> all_produttori=FactoryDao.getIstance().getProduttoreDao().getAllProduttori();
		List<Stabilimento> stabilimentiOfprod;
		for (int i=0; i<all_produttori.size(); i++){
			stabilimentiOfprod=new ArrayList<Stabilimento>(all_produttori.get(i).getStabilimentos());
			select_mapping.put(all_produttori.get(i), stabilimentiOfprod);
		}
		
		return NONE;
	}

	@Override
	public ProdottoModel getModel() {
		return prodotto_Form;
	}

}