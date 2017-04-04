/**
 * 
 */
package it.unisalento.distributori.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.domain.CategorieFornite;
import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.domain.ProdottiErogati;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.DistributoreModel;
import it.unisalento.distributori.util.AddressTranslation;

/**
 * @author aguinaldo
 */
public class CreateDistributore extends ActionSupport implements ModelDriven<DistributoreModel>{

	private DistributoreModel distributoreModel = new DistributoreModel();
	private Distributore distributore = new Distributore();
	private Prodotto prodottoVuoto;
	private Integer idDistributore;
	private Integer quantitaIniziale = 0;
	private Integer statoAttesoRifornimento = 1;
	private Integer idCatGenerica = 5;
	private List<BigDecimal> listLatLon = new ArrayList<>();

	public void validate(){
		if (distributoreModel.getCategorieFornite().isEmpty()){
			addFieldError("categorieFornite", "Il distributore deve erogare almeno una categoria");
		}
		if (hasFieldErrors()){
			addActionError("Ci sono degli errori nell'aggiornamento delle info");
		}
	}
	
	
	public String execute() {
		try {
			String indirizzo = distributoreModel.getVia() + ", " + distributoreModel.getCivico() + ", " + distributoreModel.getCitta() + ", " + distributoreModel.getProvincia();
			distributore.setIndirizzo(indirizzo);
			listLatLon = AddressTranslation.getLatLonFromAddress(indirizzo);
			distributore.setLat(listLatLon.get(0));
			distributore.setLon(listLatLon.get(1));
			distributore.setDipendente(FactoryDao.getIstance().getDipendenteDao().get(distributoreModel.getIdDipendente(), Dipendente.class));
			distributore.setPosizioneEdificio(distributoreModel.getPosizioneEdificio());
			distributore.setStato(statoAttesoRifornimento);
			distributore.setNumPosti(Integer.parseInt(distributoreModel.getNumPosti()));
			distributore.setNumScaffali(Integer.parseInt(distributoreModel.getNumScaffali()));
			idDistributore = FactoryDao.getIstance().getDistributoreDao().set(distributore);
			distributore.setId(idDistributore);
			
			for (String idCategoria : (ArrayList<String>) distributoreModel.getCategorieFornite()) {
				CategorieFornite categoriaFornita = new CategorieFornite();
				categoriaFornita.setCategoria((FactoryDao.getIstance().getCategoriaDao().get(Integer.parseInt(idCategoria.trim()), Categoria.class)));
				categoriaFornita.setDistributore(distributore);
				FactoryDao.getIstance().getCategorieForniteDao().set(categoriaFornita);
			}
			//TODO: RIMOSSA CATEGORIA GENERICA
			// aggiungo categoria generica
//			CategorieFornite categoriaGenerica = new CategorieFornite();
//			categoriaGenerica.setDistributore(distributore);
//			categoriaGenerica.setCategoria(FactoryDao.getIstance().getCategoriaDao().get(idCatGenerica, Categoria.class));
//			FactoryDao.getIstance().getCategorieForniteDao().set(categoriaGenerica);
			// fine parsing checkboxlist
			
			//TODO: RIMOSSO INSERIMENTO PRODOTTO VUOTO NEL DISTRIBUTORE (DATABASE, NON MODEL)
//			prodottoVuoto = FactoryDao.getIstance().getProdottoDao().getProdottoVuoto();
//			for (int i = 1; i <= distributore.getNumScaffali(); i++) {
//				for (int j = 1; j <= distributore.getNumPosti(); j++) {
//					FactoryDao.getIstance().getProdottiErogatiDao().set(new ProdottiErogati(distributore, prodottoVuoto, i, j, quantitaIniziale));
//				}
//			}

		} catch (ClassCastException ce) {
			ce.printStackTrace();
			System.err.println(ce.getMessage());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return SUCCESS;
	}

	public Integer getIdDistributore() {
		return idDistributore;
	}

	public void setIdDistributore(Integer idDistributore) {
		this.idDistributore = idDistributore;
	}

	@Override
	public DistributoreModel getModel() {
		return distributoreModel;
	}

	
	
	



	


}
