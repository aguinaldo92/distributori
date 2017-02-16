package it.unisalento.distributori.domain;
// Generated 16-feb-2017 12.50.08 by Hibernate Tools 4.3.1.Final

/**
 * ProdottiErogati generated by hbm2java
 */
public class ProdottiErogati implements java.io.Serializable {

	private Integer id;
	private Distributore distributore;
	private Prodotto prodotto;
	private Integer scaffale;
	private Integer quantita;

	public ProdottiErogati() {
	}

	public ProdottiErogati(Distributore distributore, Prodotto prodotto) {
		this.distributore = distributore;
		this.prodotto = prodotto;
	}

	public ProdottiErogati(Distributore distributore, Prodotto prodotto, Integer scaffale, Integer quantita) {
		this.distributore = distributore;
		this.prodotto = prodotto;
		this.scaffale = scaffale;
		this.quantita = quantita;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Distributore getDistributore() {
		return this.distributore;
	}

	public void setDistributore(Distributore distributore) {
		this.distributore = distributore;
	}

	public Prodotto getProdotto() {
		return this.prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public Integer getScaffale() {
		return this.scaffale;
	}

	public void setScaffale(Integer scaffale) {
		this.scaffale = scaffale;
	}

	public Integer getQuantita() {
		return this.quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

}
