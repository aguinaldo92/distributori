package it.unisalento.distributori.domain;
// Generated 13-feb-2017 11.51.49 by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import java.util.Date;

/**
 * Acquista generated by hbm2java
 */
public class Acquista implements java.io.Serializable {

	private Integer id;
	private Distributore distributore;
	private Persona persona;
	private Prodotto prodotto;
	private Date data;
	private Integer quantita;
	private BigDecimal totaleSpesa;

	public Acquista() {
	}

	public Acquista(Distributore distributore, Persona persona, Prodotto prodotto) {
		this.distributore = distributore;
		this.persona = persona;
		this.prodotto = prodotto;
	}

	public Acquista(Distributore distributore, Persona persona, Prodotto prodotto, Date data, Integer quantita,
			BigDecimal totaleSpesa) {
		this.distributore = distributore;
		this.persona = persona;
		this.prodotto = prodotto;
		this.data = data;
		this.quantita = quantita;
		this.totaleSpesa = totaleSpesa;
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

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Prodotto getProdotto() {
		return this.prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getQuantita() {
		return this.quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public BigDecimal getTotaleSpesa() {
		return this.totaleSpesa;
	}

	public void setTotaleSpesa(BigDecimal totaleSpesa) {
		this.totaleSpesa = totaleSpesa;
	}

}