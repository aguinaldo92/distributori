/**
 * 
 */
package it.unisalento.distributori.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;


import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.domain.ProdottiErogati;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.util.AddressTranslation;

/**
 * @author aguinaldo
 */
public class CreateDistributore extends ActionSupport {

	private static final long serialVersionUID = -2021736949462407465L;
	private BigDecimal lat;
	private BigDecimal lon;
	private String indirizzo;
	private String via;
	private Integer civico;
	private String citta;
	private String provincia;
	private String posizioneEdificio;
	private Integer numPosti;
	private Integer numScaffali;
	private List<Dipendente> dipendenti = new ArrayList<>();
	private Integer idDipendente;
	private HashSet<Categoria> categorieErogate;
	private List<Categoria> categorie = new ArrayList<Categoria>();
	private List<Integer> categorieFornite = new ArrayList<Integer>();
	private String categorieErogabili;
	private Distributore distributore;
	private Set<ProdottiErogati> prodottiErogatiVuoti;
	private Prodotto prodottoVuoto;
	private Integer idDistributore;
	private Integer quantitaIniziale = 0;
	private Integer statoAttesoRifornimento = 1;

	public String execute() {
		try {
			indirizzo = via + ", " + civico + ", " +  citta + ", " + provincia;
			lat = AddressTranslation.getLatLonFromAddress(indirizzo).get(0);
			lon = AddressTranslation.getLatLonFromAddress(indirizzo).get(1);
			
			// inizio parsing checkboxlist (utile da mettere in un altro metodo)
			categorieErogate = new HashSet<Categoria>();
			StringTokenizer st = new StringTokenizer(categorieErogabili, ",");
			while (st.hasMoreTokens()) {
				Integer cat = Integer.parseInt(st.nextToken().trim());
				categorieErogate.add(FactoryDao.getIstance().getCategoriaDao().get(cat, Categoria.class));
			}
			// fine parsing checkboxlist
			
			distributore = new Distributore();
			distributore.setDipendente(FactoryDao.getIstance().getDipendenteDao().get(idDipendente, Dipendente.class));
			distributore.setCategorieFornites((Set<Categoria>) categorieErogate);
			distributore.setIndirizzo(indirizzo);
			distributore.setPosizioneEdificio(posizioneEdificio);
			distributore.setStato(statoAttesoRifornimento);
			distributore.setLat(lat);
			distributore.setLon(lon);
			distributore.setNumPosti(numPosti);
			distributore.setNumScaffali(numScaffali);
			
			idDistributore = (Integer) FactoryDao.getIstance().getDistributoreDao().set(distributore);
			distributore.setId(idDistributore);

			prodottoVuoto = FactoryDao.getIstance().getProdottoDao().getProdottoVuoto();
			prodottiErogatiVuoti = new HashSet<ProdottiErogati>();
			for (int i = 1; i <= numScaffali; i++) {
				for (int j = 1; j <= numPosti; j++) {
					prodottiErogatiVuoti.add(new ProdottiErogati(distributore, prodottoVuoto, i, j, quantitaIniziale));
				}
			}
			distributore.setProdottiErogatis(prodottiErogatiVuoti);

		} catch (ClassCastException ce) {
			ce.printStackTrace();
			System.err.println(ce.getMessage());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return SUCCESS;
	}
	

	public String getPosizioneEdificio() {
		return posizioneEdificio;
	}

	public void setPosizioneEdificio(String posizioneEdificio) {
		this.posizioneEdificio = posizioneEdificio;
	}

	public Integer getNumPosti() {
		return numPosti;
	}

	public void setNumPosti(Integer numPosti) {
		this.numPosti = numPosti;
	}

	public Integer getNumScaffali() {
		return numScaffali;
	}

	public void setNumScaffali(Integer numScaffali) {
		this.numScaffali = numScaffali;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLon() {
		return lon;
	}

	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}

	public String getCategorieErogabili() {
		return categorieErogabili;
	}

	public void setCategorieErogabili(String categorieErogabili) {
		this.categorieErogabili = categorieErogabili;
	}

	public Integer getIdDistributore() {
		return idDistributore;
	}

	public void setIdDistributore(Integer idDistributore) {
		this.idDistributore = idDistributore;
	}

	public Integer getIdDipendente() {
		return idDipendente;
	}

	public void setIdDipendente(Integer idDipendente) {
		this.idDipendente = idDipendente;
	}
	
	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public Integer getCivico() {
		return civico;
	}

	public void setCivico(Integer civico) {
		this.civico = civico;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	public List<Dipendente> getDipendenti() {
		return dipendenti;
	}


	public void setDipendenti(List<Dipendente> dipendenti) {
		this.dipendenti = dipendenti;
	}


	public HashSet<Categoria> getCategorieErogate() {
		return categorieErogate;
	}


	public void setCategorieErogate(HashSet<Categoria> categorieErogate) {
		this.categorieErogate = categorieErogate;
	}


	public List<Categoria> getCategorie() {
		return categorie;
	}


	public void setCategorie(List<Categoria> categorie) {
		this.categorie = categorie;
	}


	public List<Integer> getCategorieFornite() {
		return categorieFornite;
	}


	public void setCategorieFornite(List<Integer> categorieFornite) {
		this.categorieFornite = categorieFornite;
	}

	
	
	



	


}
