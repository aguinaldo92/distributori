/**
 * 
 */
package it.unisalento.distributori.dao;

import it.unisalento.distributori.domain.Feedback;

/**
 * @author aguinaldo
 *
 */
public interface FeedbackDao extends BaseDao<Feedback>{
	public Long getNumMessaggiNonLetti();

}
