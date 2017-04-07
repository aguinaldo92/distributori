/**
 * 
 */
package it.unisalento.distributori.dao;

import java.util.ArrayList;

import it.unisalento.distributori.domain.Distributore;

/**
 * @author aguinaldo
 *
 */
public interface DistributoreDao extends BaseDao<Distributore> {
	public ArrayList<Distributore> getDistributoriByIdDipendenteSortedByStato(Integer idDipendente);
	public Long getNumDistributoriNonOk();
	public Integer getUpdatedStato(Integer idDistributore);
	
}