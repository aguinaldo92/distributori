package it.unisalento.distributori.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.sun.mail.imap.protocol.Item;

import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.domain.Famiglia;
import it.unisalento.distributori.domain.FamiglieProdotto;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.domain.Produttore;
import it.unisalento.distributori.domain.Stabilimento;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.ProdottoModel;

public class ProdottoDetail extends ActionSupport implements Preparable{
	
	private int idProdotto;

	private ProdottoModel prodotto = new ProdottoModel();
	
	private List<Categoria> all_categ = new ArrayList<Categoria>();
	private List<Famiglia> famiglie = new ArrayList<Famiglia>();

	Map select_mapping =new HashMap();
	
	public String execute () throws Exception {
		System.out.println("ProdottoDetail -> execute()");
		System.out.println("ID prodotto da visualizzare="+idProdotto);
		
		Prodotto prod_scelto=FactoryDao.getIstance().getProdottoDao().get(idProdotto, Prodotto.class);
		prodotto.setDescrizione(prod_scelto.getDescrizione());
		prodotto.setFoto(prod_scelto.getFoto());
		prodotto.setCategoria(prod_scelto.getCategoria());
		prodotto.setProduttore(prod_scelto.getStabilimento().getProduttore());
		prodotto.setStabilimento(prod_scelto.getStabilimento());
		prodotto.setIngredienti(prod_scelto.getIngredienti());
		prodotto.setNome(prod_scelto.getNome());
		prodotto.setPreparazione(prod_scelto.getPreparazione());
		prodotto.setPrezzo(prod_scelto.getPrezzo());
		prodotto.setSconto(prod_scelto.getScontoUtenti());
		
		List<FamiglieProdotto> f = new ArrayList<FamiglieProdotto>();
		f.addAll(prod_scelto.getFamiglieProdottos());
		List<Integer> IDsf = new ArrayList<Integer>();
		for (int i=0;i<f.size();i++){
			IDsf.add(f.get(i).getFamiglia().getId());
		}
		prodotto.setIDsfamiglie(IDsf);
		
		all_categ=FactoryDao.getIstance().getCategoriaDao().getAllCategorie();
		famiglie=FactoryDao.getIstance().getFamigliaDao().getAll(Famiglia.class);

		System.out.println("Prodotto ottenuto: "+prodotto.getNome());
		return SUCCESS;
	}
	
	@Override
	public void prepare() throws Exception {
		
		//caricamento delle doubleselect per Produttore e Stabilimento
		List<Produttore> all_produttori=FactoryDao.getIstance().getProduttoreDao().getAllProduttori();
		List<Stabilimento> stabilimentiOfprod;
		for (int i=0; i<all_produttori.size(); i++){
			stabilimentiOfprod=new ArrayList<Stabilimento>(all_produttori.get(i).getStabilimentos());
			select_mapping.put(all_produttori.get(i), stabilimentiOfprod);
		}
	}
	

	public Map getSelect_mapping() {
		return select_mapping;
	}

	public void setSelect_mapping(Map select_mapping) {
		this.select_mapping = select_mapping;
	}

	public List<Famiglia> getFamiglie() {
		return famiglie;
	}

	public void setFamiglie(List<Famiglia> famiglie) {
		this.famiglie = famiglie;
	}
	
	public ProdottoModel getProdotto() {
		return prodotto;
	}

	public void setProdotto(ProdottoModel prodotto) {
		this.prodotto = prodotto;
	}
	
	public List<Categoria> getAll_categ() {
		return all_categ;
	}


	public void setAll_categ(List<Categoria> all_categ) {
		this.all_categ = all_categ;
	}


	public int getIdProdotto() {
		return idProdotto;
	}


	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

}