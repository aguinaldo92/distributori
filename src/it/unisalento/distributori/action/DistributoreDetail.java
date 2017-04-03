/**
 * 
 */
package it.unisalento.distributori.action;

import java.util.ArrayList;
import com.opensymphony.xwork2.ActionSupport;
import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.DistributoreModel;

/**
 * @author aguinaldo
 * TODO: spostare i getter e setter nel model distributore
 */
public class DistributoreDetail extends ActionSupport {

	private static final long serialVersionUID = 1650970836755821838L;
//	private List<Integer> categorieFornite = new ArrayList<Integer>();
	private Integer idDistributore;
	private Distributore distributore;
//	private List<Dipendente> dipendenti = new ArrayList<Dipendente>();
//	private Integer idDipendente ;
//	private List<Categoria> categorie = new ArrayList<Categoria>();
//	private BigDecimal lat;
//	private BigDecimal lon;
//	private String via;
//	private Integer stato;
//	private Integer civico;
//	private String citta;
//	private String provincia;
//	private String posizioneEdificio;
//	private Integer numPosti;
//	private Integer numScaffali;
	private DistributoreModel distributoreModel;

	public String execute() {

		try {
			distributoreModel = new DistributoreModel();
			distributore = FactoryDao.getIstance().getDistributoreDao().get(idDistributore, Distributore.class);
			//categorieFornite = FactoryDao.getIstance().getCategorieForniteDao().getIdsCategorieForniteByDistributore(idDistributore);
			//categorie = FactoryDao.getIstance().getCategoriaDao().getAllCategorie();
			//dipendenti = FactoryDao.getIstance().getDipendenteDao().getAll(Dipendente.class);
			//idDipendente = distributore.getDipendente().getPersonaId();
			String[] st = distributore.getIndirizzo().split(",");
			//lat = distributore.getLat();
			//lon = distributore.getLon();
			
			//via = st[0].trim();
			//civico = Integer.parseInt(st[1].trim());
			//citta = st[2].trim();
			//provincia = st[3].trim();
			//posizioneEdificio = distributore.getPosizioneEdificio();
			//numPosti = distributore.getNumPosti();
			//numScaffali = distributore.getNumScaffali();
			//stato = distributore.getStato();
			
			distributoreModel.setIdDipendente(distributore.getDipendente().getPersonaId());
			distributoreModel.setProvincia(st[3].trim());
			distributoreModel.setPosizioneEdificio(distributore.getPosizioneEdificio());
			distributoreModel.setNumPosti(distributore.getNumPosti().toString());
			distributoreModel.setNumScaffali(distributore.getNumScaffali().toString());
			distributoreModel.setStato(distributore.getStato());
			distributoreModel.setCitta(st[2].trim());
			distributoreModel.setVia(st[0].trim());
			distributoreModel.setLat(distributore.getLat());
			distributoreModel.setLon(distributore.getLon());
			distributoreModel.setCivico(st[1]);
			distributoreModel.setCategorieFornite((ArrayList<?>) FactoryDao.getIstance().getCategorieForniteDao().getIdsCategorieForniteByDistributore(idDistributore));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}

	public Integer getIdDistributore() {
		return idDistributore;
	}

	public void setIdDistributore(Integer idDistributore) {
		this.idDistributore = idDistributore;
	}
	/*
	public Distributore getDistributore() {
		return distributore;
	}

	public void setDistributore(Distributore distributore) {
		this.distributore = distributore;
	}

	public List<Integer> getCategorieFornite() {
		return categorieFornite;
	}

	public void setCategorieFornite(List<Integer> categorieFornite) {
		this.categorieFornite = categorieFornite;
	}

	public List<Dipendente> getDipendenti() {
		return dipendenti;
	}

	public void setDipendenti(List<Dipendente> dipendenti) {
		this.dipendenti = dipendenti;
	}

	public List<Categoria> getCategorie() {
		return categorie;
	}

	public void setCategorie(List<Categoria> categorie) {
		this.categorie = categorie;
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

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public Integer getStato() {
		return stato;
	}

	public void setStato(Integer stato) {
		this.stato = stato;
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

	public Integer getIdDipendente() {
		return idDipendente;
	}

	public void setIdDipendente(Integer idDipendente) {
		this.idDipendente = idDipendente;
	}
*/
    

	public void setDistributoreModel(DistributoreModel distributoreModel) {
		this.distributoreModel = distributoreModel;
	}

	public DistributoreModel getDistributoreModel() {
		return distributoreModel;
	}
	
	






}
