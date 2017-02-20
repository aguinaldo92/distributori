package it.unisalento.distributori.domain;
// Generated 20-feb-2017 14.41.46 by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Prodotto generated by hbm2java
 */
public class Prodotto implements java.io.Serializable {

	private Integer id;
	private Categoria categoria;
	private Stabilimento stabilimento;
	private String nome;
	private String descrizione;
	private BigDecimal prezzo;
	private BigDecimal scontoUtenti;
	private String foto;
	private String preparazione;
	private String ingredienti;
	private Set famiglieProdottos = new HashSet(0);
	private Set prodottiErogatis = new HashSet(0);
	private Set acquistas = new HashSet(0);

	public Prodotto() {
	}

	public Prodotto(Categoria categoria, Stabilimento stabilimento, String nome, BigDecimal prezzo) {
		this.categoria = categoria;
		this.stabilimento = stabilimento;
		this.nome = nome;
		this.prezzo = prezzo;
	}

	public Prodotto(Categoria categoria, Stabilimento stabilimento, String nome, String descrizione, BigDecimal prezzo,
			BigDecimal scontoUtenti, String foto, String preparazione, String ingredienti, Set famiglieProdottos,
			Set prodottiErogatis, Set acquistas) {
		this.categoria = categoria;
		this.stabilimento = stabilimento;
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.scontoUtenti = scontoUtenti;
		this.foto = foto;
		this.preparazione = preparazione;
		this.ingredienti = ingredienti;
		this.famiglieProdottos = famiglieProdottos;
		this.prodottiErogatis = prodottiErogatis;
		this.acquistas = acquistas;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Stabilimento getStabilimento() {
		return this.stabilimento;
	}

	public void setStabilimento(Stabilimento stabilimento) {
		this.stabilimento = stabilimento;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public BigDecimal getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	public BigDecimal getScontoUtenti() {
		return this.scontoUtenti;
	}

	public void setScontoUtenti(BigDecimal scontoUtenti) {
		this.scontoUtenti = scontoUtenti;
	}

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getPreparazione() {
		return this.preparazione;
	}

	public void setPreparazione(String preparazione) {
		this.preparazione = preparazione;
	}

	public String getIngredienti() {
		return this.ingredienti;
	}

	public void setIngredienti(String ingredienti) {
		this.ingredienti = ingredienti;
	}

	public Set getFamiglieProdottos() {
		return this.famiglieProdottos;
	}

	public void setFamiglieProdottos(Set famiglieProdottos) {
		this.famiglieProdottos = famiglieProdottos;
	}

	public Set getProdottiErogatis() {
		return this.prodottiErogatis;
	}

	public void setProdottiErogatis(Set prodottiErogatis) {
		this.prodottiErogatis = prodottiErogatis;
	}

	public Set getAcquistas() {
		return this.acquistas;
	}

	public void setAcquistas(Set acquistas) {
		this.acquistas = acquistas;
	}

}
