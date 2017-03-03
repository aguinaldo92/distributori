package it.unisalento.distributori.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Famiglia;
import it.unisalento.distributori.domain.FamiglieProdotto;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.domain.Stabilimento;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.PersonaModel;
import it.unisalento.distributori.model.ProdottoModel;

public class UpdateProdotto extends ActionSupport implements ServletRequestAware,ModelDriven<ProdottoModel>{
	
	private String famiglia_scelta;
	private Integer idProdotto;
	private ProdottoModel prodotto_form = new ProdottoModel();
	
	private File userImage;
	private String userImageContentType;
	private String userImageFileName;
	private HttpServletRequest servletRequest;
	
	public Integer getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(Integer idProdotto) {
		this.idProdotto = idProdotto;
	}
	
	public String getFamiglia_scelta() {
		return famiglia_scelta;
	}

	public void setFamiglia_scelta(String famiglia_scelta) {
		this.famiglia_scelta = famiglia_scelta;
	}
	
	public String execute() throws Exception {

		System.out.println("Categoria selezionata: "+prodotto_form.getCategoria().getId());
		System.out.println("famiglia scelta: "+famiglia_scelta);
		
		//Scanning delle famiglie selezionate e sistemazione in prodotto_updated
		Scanner scan_fam = new Scanner(famiglia_scelta.replace(", ", " "));
		List<Integer> list_fam = new ArrayList<Integer>();
		while (scan_fam.hasNextInt()) {
			list_fam.add(scan_fam.nextInt());
		}
		scan_fam.close();
		prodotto_form.setIDsfamiglie(list_fam);
		
		Prodotto newprodotto=FactoryDao.getIstance().getProdottoDao().get(idProdotto, Prodotto.class);
		
		//aggiornamento del prodotto con le info del form
		newprodotto.setCategoria(FactoryDao.getIstance().getCategoriaDao().get(prodotto_form.getCategoria().getId(), Categoria.class));
		newprodotto.setDescrizione(prodotto_form.getDescrizione());
		newprodotto.setIngredienti(prodotto_form.getIngredienti());
		newprodotto.setNome(prodotto_form.getNome());
		newprodotto.setPreparazione(prodotto_form.getPreparazione());
		newprodotto.setPrezzo(prodotto_form.getPrezzo());
		newprodotto.setScontoUtenti(prodotto_form.getSconto());
		newprodotto.setStabilimento(FactoryDao.getIstance().getStabilimentoDao().get(prodotto_form.getStabilimento().getId(), Stabilimento.class));
		
		
		//inserimento nel DATABASE
		FactoryDao.getIstance().getProdottoDao().update(newprodotto);
		
		//eliminazione di tutte le famiglie associate al prodotto dal DATABASE
		FactoryDao.getIstance().getFamiglieProdottoDao().deleteProdottoFamilies(newprodotto.getId());
		
		//aggiornamento della relazione con le famiglie del form e inserimento nel DATABASE
		FamiglieProdotto fam_prod_obj = new FamiglieProdotto();
		for (int i=0; i<prodotto_form.getIDsfamiglie().size();i++){
			fam_prod_obj.setFamiglia(FactoryDao.getIstance().getFamigliaDao().get(prodotto_form.getIDsfamiglie().get(i), Famiglia.class));
			fam_prod_obj.setProdotto(newprodotto);
			FactoryDao.getIstance().getFamiglieProdottoDao().set(fam_prod_obj);
		}
		
		System.out.println("Numero di famiglie associate: "+prodotto_form.getIDsfamiglie().size());
		
		return SUCCESS;
	}

	@Override
	public ProdottoModel getModel() {
		// TODO Auto-generated method stub
		return prodotto_form;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
		
	}

	public File getUserImage() {
		return userImage;
	}

	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public String getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}

}