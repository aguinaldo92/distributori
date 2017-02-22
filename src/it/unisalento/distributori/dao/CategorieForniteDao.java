/**
 * 
 */
package it.unisalento.distributori.dao;

import java.util.List;

import it.unisalento.distributori.domain.CategorieFornite;

/**
 * @author aguinaldo
 *
 */
public interface CategorieForniteDao extends BaseDao<CategorieFornite>{
	
	public List<CategorieFornite> GetCategorieForniteByDistributore(Integer idDistributore);

}
