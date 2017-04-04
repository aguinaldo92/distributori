/**
 * 
 */
package it.unisalento.distributori.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.domain.CategorieFornite;
import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.DistributoreModel;
import it.unisalento.distributori.util.AddressTranslation;

/**
 * @author aguinaldo
 */
public class UpdateDistributore extends ActionSupport {

	private static final long serialVersionUID = -2021736949462407465L;
	private Distributore distributore;
	private Integer idDistributore;
	private DistributoreModel distributoreModel = new DistributoreModel();

	public String execute() {
		System.out.println("UPDATE:execute");
		try {
			distributore = FactoryDao.getIstance().getDistributoreDao().get(idDistributore, Distributore.class);
			String indirizzo = distributoreModel.getVia() + ", " + distributoreModel.getCivico() + ", " + distributoreModel.getCitta() + ", " + distributoreModel.getProvincia();
			distributore.setIndirizzo(indirizzo);
			distributore.setLat(AddressTranslation.getLatLonFromAddress(distributore.getIndirizzo()).get(0));
			distributore.setLon(AddressTranslation.getLatLonFromAddress(distributore.getIndirizzo()).get(1));
			distributore.setDipendente(FactoryDao.getIstance().getDipendenteDao().get(distributoreModel.getIdDipendente(), Dipendente.class));
			distributore.setPosizioneEdificio(distributoreModel.getPosizioneEdificio());
			distributore.setStato(distributoreModel.getStato());
//			distributore.setNumPosti(Integer.parseInt(distributoreModel.getNumPosti()));
//			distributore.setNumScaffali(Integer.parseInt(distributoreModel.getNumScaffali()));
			FactoryDao.getIstance().getDistributoreDao().update(distributore);
//			for (Iterator iterator = distributore.getCategorieFornites().iterator(); iterator.hasNext();) {
//				CategorieFornite categorieDaEliminare = (CategorieFornite) iterator.next();
//				FactoryDao.getIstance().getCategorieForniteDao().delete(categorieDaEliminare);
//			}
//			for (String idCategoria : (ArrayList<String>) distributoreModel.getCategorieFornite()) {
//				CategorieFornite categoriaFornita = new CategorieFornite();
//				categoriaFornita.setCategoria((FactoryDao.getIstance().getCategoriaDao().get(Integer.parseInt(idCategoria.trim()), Categoria.class)));
//				categoriaFornita.setDistributore(distributore);
//				FactoryDao.getIstance().getCategorieForniteDao().set(categoriaFornita);
//			}

		} catch (ClassCastException ce) {
			ce.printStackTrace();
			System.err.println(ce.getMessage());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("UPDATE:returnSuccess");
		return SUCCESS;
	}
	
	
	public void validate(){
		ServletActionContext.getRequest().setAttribute("distributoreModel", distributoreModel);
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



	public DistributoreModel getDistributoreModel() {
		return distributoreModel;
	}


	public void setDistributoreModel(DistributoreModel distributoreModel) {
		this.distributoreModel = distributoreModel;
	}








	
	
	



	


}
