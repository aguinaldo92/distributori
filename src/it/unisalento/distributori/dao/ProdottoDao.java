package it.unisalento.distributori.dao;

import java.util.List;

import it.unisalento.distributori.domain.Prodotto;

public interface ProdottoDao extends BaseDao<Prodotto>{
	public List<Prodotto> getAllProdotti();
	public List<Prodotto> getAllProdottiFiltrati(List<String> list_fam_IDs, List<String> list_categ_IDs);
}
