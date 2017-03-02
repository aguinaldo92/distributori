package it.unisalento.distributori.model;

public class DettaglioDistributoreModel {
	private Integer idProdotto;
	private Integer idProdottoErogato;
	private String nomeProdottoErogato;
	private Integer quantita;

	
	
	public Integer getIdProdotto() {
		return idProdotto;
	}
	public void setIdProdotto(Integer idProdotto) {
		this.idProdotto = idProdotto;
	}
	public Integer getIdProdottoErogato() {
		return idProdottoErogato;
	}
	public void setIdProdottoErogato(Integer idProdottoErogato) {
		this.idProdottoErogato = idProdottoErogato;
	}
	public String getNomeProdottoErogato() {
		return nomeProdottoErogato;
	}
	public void setNomeProdottoErogato(String nomeProdottoErogato) {
		this.nomeProdottoErogato = nomeProdottoErogato;
	}
	public Integer getQuantita() {
		return quantita;
	}
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	
	




}
