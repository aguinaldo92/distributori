package it.unisalento.distributori.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Famiglia;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.PersonaModel;
import it.unisalento.distributori.model.ProdottoModel;

/***
 * 
 * Per sfruttare il meccaniscmo di validazione all'interno della Action
 * è necessario estendere la classe con ActionSupport (ovviamente dovete fare l'import!!!!)
 *
 *	Per sfuttare l'interceptor ModelDriven la action deve implementare l'interfaccia ModelDriven
 */
public class UpdateProdotto extends ActionSupport implements ModelDriven<ProdottoModel>{
	
	private String famiglia_scelta;
	private ProdottoModel prodotto_updated = new ProdottoModel();
	

	public String getFamiglia_scelta() {
		return famiglia_scelta;
	}

	public void setFamiglia_scelta(String famiglia_scelta) {
		this.famiglia_scelta = famiglia_scelta;
	}
	
	public String execute() {

		System.out.println("Categoria selezionata: "+prodotto_updated.getCategoria().getId());
		System.out.println("famiglia scelta: "+famiglia_scelta);
		Scanner scan_fam = new Scanner(famiglia_scelta.replace(", ", " "));
		List<Integer> list_fam = new ArrayList<Integer>();
		while (scan_fam.hasNextInt()) {
			list_fam.add(scan_fam.nextInt());
		}
		scan_fam.close();
		prodotto_updated.setIDsfamiglie(list_fam);
		
		System.out.println("Famiglie selezionate: "+prodotto_updated.getIDsfamiglie().size());
		
		return SUCCESS;
	}

	@Override
	public ProdottoModel getModel() {
		// TODO Auto-generated method stub
		return prodotto_updated;
	}

}