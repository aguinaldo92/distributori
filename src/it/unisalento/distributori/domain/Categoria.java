package it.unisalento.distributori.domain;
// Generated 21-feb-2017 15.59.54 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Categoria generated by hbm2java
 */
public class Categoria implements java.io.Serializable {
	private static final long serialVersionUID = 1715603835756649266L;
	private Integer id;
	private String nome;
	private Set<Prodotto> prodottos = new HashSet<Prodotto>(0);
	private Set<CategorieFornite> categorieFornites = new HashSet<CategorieFornite>(0);

	public Categoria() {
	}

	public Categoria(String nome) {
		this.nome = nome;
	}

	public Categoria(String nome, Set<Prodotto> prodottos, Set<CategorieFornite> categorieFornites) {
		this.nome = nome;
		this.prodottos = prodottos;
		this.categorieFornites = categorieFornites;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Prodotto> getProdottos() {
		return this.prodottos;
	}

	public void setProdottos(Set<Prodotto> prodottos) {
		this.prodottos = prodottos;
	}

	public Set<CategorieFornite> getCategorieFornites() {
		return this.categorieFornites;
	}

	public void setCategorieFornites(Set<CategorieFornite> categorieFornites) {
		this.categorieFornites = categorieFornites;
	}

}
