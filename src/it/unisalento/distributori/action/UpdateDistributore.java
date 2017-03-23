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
	private Set<Categoria> categorie;
	private ArrayList<Integer> idsCategorie;
	private DistributoreModel distributoreModel = new DistributoreModel();

	public String execute() {
		System.out.println("UPDATE:execute");
		try {
			distributore = new Distributore();
			distributore.setIndirizzo(distributoreModel.getVia() + "  " + distributoreModel.getCivico() + ", " + distributoreModel.getCitta() + ", " + distributoreModel.getProvincia());
			distributore.setLat(AddressTranslation.getLatLonFromAddress(distributore.getIndirizzo()).get(0));
			distributore.setLon(AddressTranslation.getLatLonFromAddress(distributore.getIndirizzo()).get(1));
			distributore.setDipendente(FactoryDao.getIstance().getDipendenteDao().get(distributoreModel.getIdDipendente(), Dipendente.class));
			idsCategorie = (ArrayList<Integer>) distributoreModel.getCategorieFornite();
			Iterator<Integer> categorieIterator = idsCategorie.iterator();
			categorie = new HashSet<Categoria>();
			while(categorieIterator.hasNext()){
				categorie.add(FactoryDao.getIstance().getCategoriaDao().get(categorieIterator.next(), Categoria.class));
			}
			distributore.setCategorieFornites(categorie);
			distributore.setIndirizzo(distributoreModel.getIndirizzo());
			distributore.setPosizioneEdificio(distributoreModel.getPosizioneEdificio());
			distributore.setStato(distributoreModel.getStato());
			distributore.setNumPosti(distributoreModel.getNumPosti());
			distributore.setNumScaffali(distributoreModel.getNumScaffali());

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
		System.out.println("UPDATE:VALIDATEE");
		ServletActionContext.getRequest().setAttribute("distributoreModel", distributoreModel);
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
