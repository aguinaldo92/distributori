package it.unisalento.distributori.dao;

import java.util.ArrayList;

import it.unisalento.distributori.domain.ProdottiErogati;
import it.unisalento.distributori.domain.Prodotto;

public interface ProdottoDao extends BaseDao<Prodotto>{
	public ArrayList<Prodotto> getProdottiCompatibiliByDistributore(Integer idDistributore);
		
}
