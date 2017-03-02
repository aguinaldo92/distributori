package it.unisalento.distributori.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.domain.Produttore;
import it.unisalento.distributori.domain.Stabilimento;

public class ProdottoModel {
	
	private String nome;
	private String foto;
	private Produttore produttore;
	private Stabilimento stabilimento;
	private Categoria categoria;
	private List<Integer> IDsfamiglie;
	private String descrizione;
	private String ingredienti;
	private String preparazione;
	private BigDecimal prezzo;
	private BigDecimal sconto;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getIngredienti() {
		return ingredienti;
	}
	public void setIngredienti(String ingredienti) {
		this.ingredienti = ingredienti;
	}
	public String getPreparazione() {
		return preparazione;
	}
	public void setPreparazione(String preparazione) {
		this.preparazione = preparazione;
	}
	public BigDecimal getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}
	public BigDecimal getSconto() {
		return sconto;
	}
	public void setSconto(BigDecimal sconto) {
		this.sconto = sconto;
	}

	public Produttore getProduttore() {
		return produttore;
	}
	public void setProduttore(Produttore produttore) {
		this.produttore = produttore;
	}
	public Stabilimento getStabilimento() {
		return stabilimento;
	}
	public void setStabilimento(Stabilimento stabilimento) {
		this.stabilimento = stabilimento;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public List<Integer> getIDsfamiglie() {
		return IDsfamiglie;
	}
	public void setIDsfamiglie(List<Integer> iDsfamiglie) {
		IDsfamiglie = iDsfamiglie;
	}
}
