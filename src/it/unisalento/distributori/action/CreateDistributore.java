/**
 * 
 */
package it.unisalento.distributori.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 4392593039105190818L;
	private DistributoreModel distributoreModel = new DistributoreModel();
	private Distributore distributore = new Distributore();
	private Prodotto prodottoVuoto;
	private Integer idDistributore;
	private Integer quantitaIniziale = 0;
	private Integer statoAttesoRifornimento = 1;
	private Integer idCategoriaProdottoVuoto = 5;
	private List<BigDecimal> listLatLon = new ArrayList<>();
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	public String execute() {
		try {
			logger.debug("execute()");
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
			// aggiungo categoria generica
			CategorieFornite categoriaGenerica = new CategorieFornite();
			categoriaGenerica.setDistributore(distributore);
			categoriaGenerica.setCategoria(FactoryDao.getIstance().getCategoriaDao().get(idCategoriaProdottoVuoto, Categoria.class));
			FactoryDao.getIstance().getCategorieForniteDao().set(categoriaGenerica);
			// fine parsing checkboxlist

			prodottoVuoto = FactoryDao.getIstance().getProdottoDao().getProdottoVuoto();
			for (int scaffale = 1; scaffale <= distributore.getNumScaffali(); scaffale++) {
				for (int posto = 1; posto <= distributore.getNumPosti(); posto++) {
					FactoryDao.getIstance().getProdottiErogatiDao().set(new ProdottiErogati(distributore, prodottoVuoto, scaffale, posto, quantitaIniziale));
				}
			}
			
			return SUCCESS;

		} catch (ClassCastException ce) {
			logger.error("Impossibile registrare il nuovo Distributore a causa di un errore nel settare le categorie fornite",ce);
			addActionError("Impossibile salvare il distributore a causa di un problema nell'impostare le categorie. Riprovare o contattare l'amministratore");
			return INPUT;

		} catch (Exception e) {
			logger.error("Impossibile registrare il nuovo Distributore a causa di un errore ",e);
			return ERROR;
		}
	}

	public void validate(){
		logger.debug("validate()");
		if (distributoreModel.getCategorieFornite().isEmpty()){
			addFieldError("categorieFornite", "Il distributore deve erogare almeno una categoria");
		}
		if (hasFieldErrors()){
			addActionError("Ci sono degli errori nell'aggiornamento delle info");
		}
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
