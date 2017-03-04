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

public class AddProdotto extends ActionSupport implements ModelDriven<ProdottoModel>{
	
	private int sconto_percentuale;
	private ProdottoModel prodotto_Form = new ProdottoModel();
	private String famiglia_scelta;

	Map select_mapping =new HashMap();
	private List<Categoria> all_categ = new ArrayList<Categoria>();
	private List<Famiglia> famiglie = new ArrayList<Famiglia>();
	

	public int getSconto_percentuale() {
		return sconto_percentuale;
	}

	public void setSconto_percentuale(int sconto_percentuale) {
		this.sconto_percentuale = sconto_percentuale;
	}
	
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

	public String execute() throws Exception{
		
		System.out.println("AddProdotto: execute()");

		Prodotto new_prodotto=new Prodotto();
		//settaggio caratteristiche prodotto
		new_prodotto.setCategoria(FactoryDao.getIstance().getCategoriaDao().get(prodotto_Form.getCategoria().getId(), Categoria.class));
		new_prodotto.setDescrizione(prodotto_Form.getDescrizione());
		new_prodotto.setIngredienti(prodotto_Form.getIngredienti());
		new_prodotto.setNome(prodotto_Form.getNome());
		new_prodotto.setPreparazione(prodotto_Form.getPreparazione());
		new_prodotto.setPrezzo(prodotto_Form.getPrezzo());
		new_prodotto.setScontoUtenti(BigDecimal.valueOf(sconto_percentuale).divide(BigDecimal.valueOf(100)));
		new_prodotto.setStabilimento(FactoryDao.getIstance().getStabilimentoDao().get(prodotto_Form.getStabilimento().getId(), Stabilimento.class));
		new_prodotto.setFoto("images/no_image.jpg");
		//inserimento nel DATABASE del prodotto
		new_prodotto.setId(FactoryDao.getIstance().getProdottoDao().set(new_prodotto));
		
		//settaggio delle famiglie e inserimento nel DATABASE
		prodotto_Form.setIDsfamiglie(famiglia_scelta);
		FamiglieProdotto fam_prod_obj = new FamiglieProdotto();
		for (int i=0; i<prodotto_Form.getIDsfamiglie().size();i++){
			fam_prod_obj.setFamiglia(FactoryDao.getIstance().getFamigliaDao().get(prodotto_Form.getIDsfamiglie().get(i), Famiglia.class));
			fam_prod_obj.setProdotto(new_prodotto);
			FactoryDao.getIstance().getFamiglieProdottoDao().set(fam_prod_obj);
		}
		
		ServletActionContext.getRequest().setAttribute("idNewProdotto", new_prodotto.getId());
				
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