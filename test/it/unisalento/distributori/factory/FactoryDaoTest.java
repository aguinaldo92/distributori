package it.unisalento.distributori.factory;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import it.unisalento.distributori.dao.CategoriaDao;
import it.unisalento.distributori.dao.CategorieForniteDao;
import it.unisalento.distributori.dao.DipendenteDao;
import it.unisalento.distributori.dao.DistributoreDao;
import it.unisalento.distributori.dao.FamigliaDao;
import it.unisalento.distributori.dao.FamiglieProdottoDao;
import it.unisalento.distributori.dao.FeedbackDao;
import it.unisalento.distributori.dao.PersonaDao;
import it.unisalento.distributori.dao.ProdottiErogatiDao;
import it.unisalento.distributori.dao.ProdottoDao;
import it.unisalento.distributori.dao.ProduttoreDao;
import it.unisalento.distributori.dao.RifornisceDao;
import it.unisalento.distributori.dao.StabilimentoDao;

public class FactoryDaoTest {
	
	FactoryDao istance = FactoryDao.getIstance();
	
	@Test
	public void testGetPersonaDao() throws Exception {
		PersonaDao dao = istance.getPersonaDao();
		assertNotNull(dao);
	}

	@Test
	public void testGetDipendenteDao() throws Exception {
		DipendenteDao dao = istance.getDipendenteDao();
		assertNotNull(dao);
	}

	@Test
	public void testGetProdottoDao() throws Exception {
		ProdottoDao dao = istance.getProdottoDao();
		assertNotNull(dao);
	}

	@Test
	public void testGetCategoriaDao() throws Exception {
		CategoriaDao dao = istance.getCategoriaDao();
		assertNotNull(dao);
	}

	@Test
	public void testGetFamigliaDao() throws Exception {
		FamigliaDao dao = istance.getFamigliaDao(); 
		assertNotNull(dao);
	}

	@Test
	public void testGetProduttoreDao() throws Exception {
		ProduttoreDao dao = istance.getProduttoreDao(); 
		assertNotNull(dao);
	}

	@Test
	public void testGetStabilimentoDao() throws Exception {
		StabilimentoDao dao = istance.getStabilimentoDao(); 
		assertNotNull(dao);
	}

	@Test
	public void testGetDistributoreDao() throws Exception {
		DistributoreDao dao = istance.getDistributoreDao(); 
		assertNotNull(dao);
	}

	@Test
	public void testGetCategorieForniteDao() throws Exception {
		CategorieForniteDao dao = istance.getCategorieForniteDao(); 
		assertNotNull(dao);
	}

	@Test
	public void testGetProdottiErogatiDao() throws Exception {
		ProdottiErogatiDao dao = istance.getProdottiErogatiDao(); 
		assertNotNull(dao);
	}

	@Test
	public void testGetFamiglieProdottoDao() throws Exception {
		FamiglieProdottoDao dao = istance.getFamiglieProdottoDao(); 
		assertNotNull(dao);
	}

	@Test
	public void testGetFeedbackDao() throws Exception {
		FeedbackDao dao = istance.getFeedbackDao(); 
		assertNotNull(dao);
	}

	@Test
	public void testGetRifornisceDao() throws Exception {
		RifornisceDao dao = istance.getRifornisceDao(); 
		assertNotNull(dao);
	}

}
