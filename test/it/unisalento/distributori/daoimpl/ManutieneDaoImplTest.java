package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisalento.distributori.dao.ManutieneDao;
import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.domain.Manutiene;
import it.unisalento.distributori.factory.FactoryDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManutieneDaoImplTest {

	ManutieneDao dao=FactoryDao.getIstance().getManutieneDao();
	
	@Test
	public void testGetManutenzionePendenteByDistributore() throws Exception {
		Manutiene manutenzione = FactoryDao.getIstance().getManutieneDao().getManutenzionePendenteByDistributore(1);
		
		assertNull(manutenzione.getDataFine());
	}
	
	@Test
	public void testCRUD() throws Exception {
		
		Integer id;
		Manutiene manutenzione=new Manutiene();
		
		//set
		manutenzione.setDipendente(FactoryDao.getIstance().getDipendenteDao().get(8, Dipendente.class));
		manutenzione.setDistributore(FactoryDao.getIstance().getDistributoreDao().get(1, Distributore.class));
		manutenzione.setDataInizio(new Date());
		manutenzione.setDataFine(null);
		
		manutenzione.setId(dao.set(manutenzione));
		
		id=manutenzione.getId();
		
		assertTrue(manutenzione.getId()>0);
		
		//get (by ID)
		manutenzione=dao.get(id, Manutiene.class);
		
		assertNotNull(manutenzione);
		assertEquals(id, manutenzione.getId());
		
		//update
		manutenzione=dao.get(id, Manutiene.class);
		manutenzione.setDataFine(new Date());
		dao.update(manutenzione);
		manutenzione=dao.get(id, Manutiene.class);
		
		assertNotNull(manutenzione.getDataFine());
		
		//delete
		manutenzione=dao.get(id, Manutiene.class);
		dao.delete(manutenzione);
		manutenzione=dao.get(id, Manutiene.class);
		
		assertEquals(null, manutenzione);
	}

	@Test
	public void testGetAll() throws Exception {
		List<Manutiene> manutenzioni = dao.getAll(Manutiene.class);
		
		assertNotNull(manutenzioni);
		assertTrue(manutenzioni.size()>0);
	}

	@Test
	public void testGetAllSortedBy() throws Exception {//get all sorted by
		List<Manutiene> manutenzioni = dao.getAllSortedBy(Manutiene.class, "dataInizio");
		Date data1;
		Date data2;
		boolean error = false;
		for(int i=1; i<manutenzioni.size() && !error; i++){
			data1 = manutenzioni.get(i-1).getDataInizio();
			data2 = manutenzioni.get(i).getDataInizio();
			if(data1.compareTo(data2)>0)
				error=true;
		}
		assertTrue(!error);
	}

}
