package it.unisalento.distributori.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdottoModel {
	
	private String nome;
	private String foto;
	private int idProduttore;
	private int idStabilimento;
	private int idCategoria;
	private List<Integer> idFamiglie=new ArrayList<Integer>();
	private String descrizione;
	private String ingredienti;
	private String preparazione;
	
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
	public int getIdProduttore() {
		return idProduttore;
	}
	public void setIdProduttore(int idProduttore) {
		this.idProduttore = idProduttore;
	}
	public int getIdStabilimento() {
		return idStabilimento;
	}
	public void setIdStabilimento(int idStabilimento) {
		this.idStabilimento = idStabilimento;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public List<Integer> getIdFamiglie() {
		return idFamiglie;
	}
	public void setIdFamiglie(List<Integer> idFamiglie) {
		this.idFamiglie = idFamiglie;
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
	private BigDecimal prezzo;
	private BigDecimal sconto;
}
