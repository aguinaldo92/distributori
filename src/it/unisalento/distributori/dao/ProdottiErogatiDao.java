/**
 * 
 */
package it.unisalento.distributori.dao;

import java.util.ArrayList;
import it.unisalento.distributori.domain.ProdottiErogati;

/**
 * @author aguinaldo
 *
 */
public interface ProdottiErogatiDao extends BaseDao<ProdottiErogati>{
	
	public ArrayList<ProdottiErogati> GetProdottiErogatiByDistributore(Integer idDistributore);
	public ArrayList<ProdottiErogati> GetProdottiScarseggiantiByDistributore(Integer idDistributore, Integer quantitaMinima);
	public ArrayList<String> GetNomiProdottiScarseggiantiByDistributore(Integer idDistributore, Integer quantitaMinima);

}
