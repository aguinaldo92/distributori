package it.unisalento.distributori.domain;
// Generated 21-feb-2017 15.59.54 by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Prodotto generated by hbm2java
 */
public class Prodotto implements java.io.Serializable {

	private static final long serialVersionUID = 4751307365786704104L;
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
	private Set<FamiglieProdotto> famiglieProdottos = new HashSet<FamiglieProdotto>(0);
	private Set<ProdottiErogati> prodottiErogatis = new HashSet<ProdottiErogati>(0);
	private Set<Acquista> acquistas = new HashSet<Acquista>(0);

	public Prodotto() {
	}

	public Prodotto(Categoria categoria, Stabilimento stabilimento, String nome, BigDecimal prezzo) {
		this.categoria = categoria;
		this.stabilimento = stabilimento;
		this.nome = nome;
		this.prezzo = prezzo;
	}

	public Prodotto(Categoria categoria, Stabilimento stabilimento, String nome, String descrizione, BigDecimal prezzo,
			BigDecimal scontoUtenti, String foto, String preparazione, String ingredienti, Set<FamiglieProdotto> famiglieProdottos,
			Set<ProdottiErogati> prodottiErogatis, Set<Acquista> acquistas) {
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

	public Set<FamiglieProdotto> getFamiglieProdottos() {
		return this.famiglieProdottos;
	}

	public void setFamiglieProdottos(Set<FamiglieProdotto> famiglieProdottos) {
		this.famiglieProdottos = famiglieProdottos;
	}

	public Set<ProdottiErogati> getProdottiErogatis() {
		return this.prodottiErogatis;
	}

	public void setProdottiErogatis(Set<ProdottiErogati> prodottiErogatis) {
		this.prodottiErogatis = prodottiErogatis;
	}

	public Set<Acquista> getAcquistas() {
		return this.acquistas;
	}

	public void setAcquistas(Set<Acquista> acquistas) {
		this.acquistas = acquistas;
	}
	public BigDecimal getScontoUtenti() {
		return scontoUtenti;
	}

	public void setScontoUtenti(BigDecimal scontoUtenti) {
		this.scontoUtenti = scontoUtenti;
	}

}
