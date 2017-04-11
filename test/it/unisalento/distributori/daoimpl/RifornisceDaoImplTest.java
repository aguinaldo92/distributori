package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisalento.distributori.dao.RifornisceDao;
import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.domain.Rifornisce;
import it.unisalento.distributori.factory.FactoryDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RifornisceDaoImplTest {

	RifornisceDao dao=FactoryDao.getIstance().getRifornisceDao();
	
	@Test
	public void testCRUD() throws Exception {
		
		Integer id;
		Rifornisce rifornisce=new Rifornisce();
		Date data = new Date();
		
		//set
		rifornisce.setData(data);
		rifornisce.setDipendente(FactoryDao.getIstance().getDipendenteDao().get(19, Dipendente.class));
		rifornisce.setDistributore(FactoryDao.getIstance().getDistributoreDao().get(1, Distributore.class));
		
		rifornisce.setId(dao.set(rifornisce));
		
		id=rifornisce.getId();
		
		assertTrue(rifornisce.getId()>0);
		
		//get (by ID)
		rifornisce=dao.get(id, Rifornisce.class);
		
		assertNotNull(rifornisce);
		assertEquals(id, rifornisce.getId());
		
		//update
		Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.DATE, 3);
        data = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String data_string=format.format(data);
        
		rifornisce=dao.get(id, Rifornisce.class);
		rifornisce.setData(data);
		dao.update(rifornisce);
		rifornisce=dao.get(id, Rifornisce.class);
		Date data_db = rifornisce.getData();
		String data_db_string = format.format(data_db);
		
		assertTrue(data_string.compareTo(data_db_string)==0);
		
		//delete
		rifornisce=dao.get(id, Rifornisce.class);
		dao.delete(rifornisce);
		rifornisce=dao.get(id, Rifornisce.class);
		
		assertEquals(null, rifornisce);
	}

	@Test
	public void testGetAll() throws Exception {
		List<Rifornisce> rifornimenti = dao.getAll(Rifornisce.class);
		
		assertNotNull(rifornimenti);
		assertTrue(rifornimenti.size()>0);
	}

	@Test
	public void testGetAllSortedBy() throws Exception {//get all sorted by
		List<Rifornisce> rifornimenti = dao.getAllSortedBy(Rifornisce.class, "data");
		Date data1;
		Date data2;
		boolean error = false;
		for(int i=1; i<rifornimenti.size() && !error; i++){
			data1 = rifornimenti.get(i-1).getData();
			data2 = rifornimenti.get(i).getData();
			if(data1.compareTo(data2)>0)
				error=true;
		}
		assertTrue(!error);
	}

}
