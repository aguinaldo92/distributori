package it.unisalento.distributori.domain;
// Generated 20-feb-2017 14.41.46 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Stabilimento generated by hbm2java
 */
public class Stabilimento implements java.io.Serializable {

	private Integer id;
	private Produttore produttore;
	private String citta;
	private String provincia;
	private Set prodottos = new HashSet(0);

	public Stabilimento() {
	}

	public Stabilimento(Produttore produttore) {
		this.produttore = produttore;
	}

	public Stabilimento(Produttore produttore, String citta, String provincia, Set prodottos) {
		this.produttore = produttore;
		this.citta = citta;
		this.provincia = provincia;
		this.prodottos = prodottos;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Produttore getProduttore() {
		return this.produttore;
	}

	public void setProduttore(Produttore produttore) {
		this.produttore = produttore;
	}

	public String getCitta() {
		return this.citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public Set getProdottos() {
		return this.prodottos;
	}

	public void setProdottos(Set prodottos) {
		this.prodottos = prodottos;
	}

}
