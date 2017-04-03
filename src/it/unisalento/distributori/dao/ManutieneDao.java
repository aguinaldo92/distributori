package it.unisalento.distributori.dao;

import it.unisalento.distributori.domain.Manutiene;

public interface ManutieneDao extends BaseDao<Manutiene>{
		
	public Manutiene getManutenzionePendenteByDistributore(int idDistributore);
}
