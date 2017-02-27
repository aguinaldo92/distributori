package it.unisalento.distributori.dao;

import java.util.List;

import it.unisalento.distributori.domain.Produttore;
import it.unisalento.distributori.domain.Stabilimento;

public interface ProduttoreDao extends BaseDao<Produttore>{
	
	public List<Stabilimento> getStabilimentiByProduttore(int id_produttore);
		
}
