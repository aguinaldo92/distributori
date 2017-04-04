/**
 * 
 */
package it.unisalento.distributori.model;

import java.math.BigDecimal;
import java.util.ArrayList;

import it.unisalento.distributori.domain.Persona;

/**
 * @author aguinaldo
 *
 */
public class DistributoreModel {
	private Integer id;
	private Integer stato;
	private String stato_String;
	private String indirizzo;
	private BigDecimal lat;
	private BigDecimal lon;
	private String via;
	private Integer civico;
	private String citta;
	private String provincia;
	private String posizioneEdificio;
	private Integer numScaffali;
	private Integer numPosti;
	private Persona dipendente;
	private Integer idDipendente;
	private ArrayList<?> categorieFornite;
	private ArrayList<?> prodottiForniti;
	private boolean inManutenzione;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStato() {
		return stato;
	}
	public void setStato(Integer stato) {
		this.stato = stato;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
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
	public String getPosizioneEdificio() {
		return posizioneEdificio;
	}
	public void setPosizioneEdificio(String posizioneEdificio) {
		this.posizioneEdificio = posizioneEdificio;
	}
	public Persona getDipendente() {
		return dipendente;
	}
	public void setDipendente(Persona dipendente) {
		this.dipendente = dipendente;
	}
	public ArrayList<?> getCategorieFornite() {
		return categorieFornite;
	}
	public void setCategorieFornite(ArrayList<?> categorieFornite) {
		this.categorieFornite = categorieFornite;
	}
	public ArrayList<?> getProdottiForniti() {
		return prodottiForniti;
	}
	public void setProdottiForniti(ArrayList<?> prodottiForniti) {
		this.prodottiForniti = prodottiForniti;
	}
	public Integer getNumScaffali() {
		return numScaffali;
	}
	public void setNumScaffali(Integer numScaffali) {
		this.numScaffali = numScaffali;
	}
	public Integer getNumPosti() {
		return numPosti;
	}
	public void setNumPosti(Integer numPosti) {
		this.numPosti = numPosti;
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
	public Integer getIdDipendente() {
		return idDipendente;
	}
	public void setIdDipendente(Integer idDipendente) {
		this.idDipendente = idDipendente;
	}
	public String getStato_String() {
		return stato_String;
	}
	public void setStato_String(String stato_String) {
		this.stato_String = stato_String;
	}
	public boolean isInManutenzione() {
		return inManutenzione;
	}
	public void setInManutenzione(boolean inManutenzione) {
		this.inManutenzione = inManutenzione;
	}
	
	
	
	
}
