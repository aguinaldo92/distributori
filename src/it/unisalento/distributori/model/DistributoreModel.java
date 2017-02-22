/**
 * 
 */
package it.unisalento.distributori.model;

import java.util.ArrayList;

/**
 * @author aguinaldo
 *
 */
public class DistributoreModel {
	private Integer id;
	private Integer stato;
	private String indirizzo;
	private String posizioneEdificio;
	private ArrayList<?> categorieFornite;
	private ArrayList<?> prodottiForniti;
	
	
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
	public String getPosizioneEdificio() {
		return posizioneEdificio;
	}
	public void setPosizioneEdificio(String posizioneEdificio) {
		this.posizioneEdificio = posizioneEdificio;
	}
	public ArrayList getCategorieFornite() {
		return categorieFornite;
	}
	public void setCategorieFornite(ArrayList categorieFornite) {
		this.categorieFornite = categorieFornite;
	}
	public ArrayList getProdottiForniti() {
		return prodottiForniti;
	}
	public void setProdottiForniti(ArrayList prodottiForniti) {
		this.prodottiForniti = prodottiForniti;
	}

	
	
}
