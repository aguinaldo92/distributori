/**
 * 
 */
package it.unisalento.distributori.daoimpl;

import it.unisalento.distributori.dao.DipendenteDao;
import it.unisalento.distributori.domain.Dipendente;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author aguinaldo
 *
 */
public class DipendenteDaoImpl extends BaseDaoImpl<Dipendente> implements DipendenteDao {

}
