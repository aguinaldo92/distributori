/**
 * 
 */
package it.unisalento.distributori.daoimpl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unisalento.distributori.dao.DistributoreDao;
import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.factory.FactoryDao;

/**
 * @author aguinaldo
 *
 */
public class DistributoreDaoImpl extends BaseDaoImpl<Distributore> implements DistributoreDao {
	private Session session;
	private Transaction tx;

	@Override
	public Integer getUpdatedStato(Integer idDistributore) {
		Integer stato = 0;
		try {
			Distributore distributore = FactoryDao.getIstance().getDistributoreDao().get(idDistributore, Distributore.class);
			Integer savedStato = distributore.getStato() == 0 ? 5 : 0; // se lo stato è 0 il sistema 
			Integer inManutenzione = FactoryDao.getIstance().getManutieneDao().getManutenzionePendenteByDistributore(idDistributore) == null ? 0 : 1;
			Integer prodottiScarseggianti = FactoryDao.getIstance().getProdottiErogatiDao().getProdottiScarseggiantiByDistributore(idDistributore).size() == 0 ? 0 : 2;

			switch (inManutenzione + prodottiScarseggianti+ savedStato) {
			case 0:
				stato = 2; // verde (tutto ok)
				break;
			case 2:
				stato = 1; //giallo (rifornimento)
				break;
			case 1:
				stato = 3; // arancione, iniziata manutenzione
				break;
			default:
				stato = 0; //rosso
				break;
			}
			return stato;

		} finally {
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Distributore> getDistributoriByIdDipendenteSortedByStato(Integer idDipendente) {
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "from Distributore d where d.dipendente.id = :idDipendente";
			Query query = session.createQuery(hql);
			query.setInteger("idDipendente", idDipendente);
			ArrayList<Distributore> listDistributore = (ArrayList<Distributore>) query.list();
			tx.commit();
			return listDistributore;

		} finally {
			session.close();
		}

	}

	@Override
	public Long getNumDistributoriNonOk() {
		Long numDistributoriNonOk;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("select count(*) as num from Distributore as D where D.stato = 0 or D.stato = 1");
			numDistributoriNonOk = (Long) query.uniqueResult();
			tx.commit();
			return numDistributoriNonOk;

		} finally {
			session.close();
		}
	}
}
