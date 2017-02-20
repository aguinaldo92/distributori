package it.unisalento.distributori.domain;
// Generated 20-feb-2017 14.41.46 by Hibernate Tools 4.3.1.Final

import java.util.Date;

/**
 * Rifornisce generated by hbm2java
 */
public class Rifornisce implements java.io.Serializable {

	private Integer id;
	private Dipendente dipendente;
	private Distributore distributore;
	private Date data;

	public Rifornisce() {
	}

	public Rifornisce(Distributore distributore) {
		this.distributore = distributore;
	}

	public Rifornisce(Dipendente dipendente, Distributore distributore, Date data) {
		this.dipendente = dipendente;
		this.distributore = distributore;
		this.data = data;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Dipendente getDipendente() {
		return this.dipendente;
	}

	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}

	public Distributore getDistributore() {
		return this.distributore;
	}

	public void setDistributore(Distributore distributore) {
		this.distributore = distributore;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}