package it.unisalento.distributori.model;

import java.util.ArrayList;

import it.unisalento.distributori.domain.ProdottiErogati;

public class DettaglioDistributoreModel {
	private Integer id;
	private Integer numScaffali;
	private Integer numPosti;
	private ArrayList<ProdottiErogati> prodottiErogati;
	private ArrayList<String> nomiCategorieFornite;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public ArrayList<ProdottiErogati> getProdottiErogati() {
		return prodottiErogati;
	}
	public void setProdottiErogati(ArrayList<ProdottiErogati> prodottiErogati) {
		this.prodottiErogati = prodottiErogati;
	}
	public ArrayList<String> getNomiCategorieFornite() {
		return nomiCategorieFornite;
	}
	public void setNomiCategorieFornite(ArrayList<String> nomiCategorieFornite) {
		this.nomiCategorieFornite = nomiCategorieFornite;
	}

}
