package it.unisalento.distributori.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.domain.Famiglia;
import it.unisalento.distributori.domain.FamiglieProdotto;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.domain.Produttore;
import it.unisalento.distributori.domain.Stabilimento;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.ProdottoModel;

public class PreloadedFieldsProdotto extends ActionSupport{

	Map select_mapping =new HashMap();
	private List<Categoria> all_categ = new ArrayList<Categoria>();
	private List<Famiglia> famiglie = new ArrayList<Famiglia>();

	public String execute() throws Exception{
		System.out.println("PreloadedFieldsProdotto");
		
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
		
		ServletActionContext.getRequest().setAttribute("all_categ", all_categ);
		ServletActionContext.getRequest().setAttribute("famiglie", famiglie);
		ServletActionContext.getRequest().setAttribute("select_mapping", select_mapping);
		
		return SUCCESS;
	}

	public Map getSelect_mapping() {
		return select_mapping;
	}

	public void setSelect_mapping(Map select_mapping) {
		this.select_mapping = select_mapping;
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
}