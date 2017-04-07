package it.unisalento.distributori.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.domain.Famiglia;
import it.unisalento.distributori.domain.Produttore;
import it.unisalento.distributori.domain.Stabilimento;
import it.unisalento.distributori.factory.FactoryDao;

public class PreloadedFieldsProdotto extends ActionSupport{
	private static final long serialVersionUID = 6255011883759782290L;
	Map<Produttore,List<Stabilimento>> select_mapping = new HashMap<>();
	private List<Categoria> all_categ = new ArrayList<Categoria>();
	private List<Famiglia> famiglie = new ArrayList<Famiglia>();
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	public String execute() {
		try{
			logger.trace("execute()");

			//caricamento delle categorie e delle famiglie
			all_categ = FactoryDao.getIstance().getCategoriaDao().getAllCategorie();
			famiglie = FactoryDao.getIstance().getFamigliaDao().getAll(Famiglia.class);

			//caricamento delle doubleselect per Produttore e Stabilimento
			List<Produttore> all_produttori = FactoryDao.getIstance().getProduttoreDao().getAllProduttori();
			for (int indexProduttore=0; indexProduttore<all_produttori.size(); indexProduttore++){
				List<Stabilimento> stabilimentiOfprod = new ArrayList<Stabilimento>(all_produttori.get(indexProduttore).getStabilimentos());
				select_mapping.put(all_produttori.get(indexProduttore), stabilimentiOfprod);
			}

			ServletActionContext.getRequest().setAttribute("all_categ", all_categ);
			ServletActionContext.getRequest().setAttribute("famiglie", famiglie);
			ServletActionContext.getRequest().setAttribute("select_mapping", select_mapping);

			return SUCCESS;
		} catch (Exception e){
			logger.error("Impossibile precaricare le informazioni relative alle categorie, alle famiglie e ai produttori del prodotto selezionato ",e);
			addActionError("Impossibile caricare l'elenco dei Distributori");
			return ERROR;
		}
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