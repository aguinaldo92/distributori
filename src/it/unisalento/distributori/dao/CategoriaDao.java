package it.unisalento.distributori.dao;

import java.util.List;

import it.unisalento.distributori.domain.Categoria;

public interface CategoriaDao extends BaseDao<Categoria>{
	public List<Categoria> getAllCategorie();
}
