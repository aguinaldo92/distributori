package it.unisalento.distributori.domain;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import it.unisalento.distributori.factory.FactoryDao;

public class DipendenteTest {

	@Test
	public void testDipendentePersona() throws Exception {
		Dipendente dipendente=new Dipendente(FactoryDao.getIstance().getPersonaDao().get(1, Persona.class));
		
		assertNotNull(dipendente);
		assertEquals("Giovanni", dipendente.getPersona().getNome());
	}

	@Test
	public void testDipendentePersonaStringSetSetSet() throws Exception {
		Set<Rifornisce> rifornisces = new HashSet<Rifornisce>();
		Set<Manutiene> manutienes = new HashSet<Manutiene>();
		Set<Distributore> distributores = new HashSet<Distributore>();
		
		Rifornisce rif = new Rifornisce(FactoryDao.getIstance().getDistributoreDao().get(1, Distributore.class));
		rifornisces.add(rif);
		rif = new Rifornisce(FactoryDao.getIstance().getDistributoreDao().get(3, Distributore.class));
		rifornisces.add(rif);
		
		Manutiene man = new Manutiene(FactoryDao.getIstance().getDistributoreDao().get(3, Distributore.class));
		manutienes.add(man);
		
		distributores.add(FactoryDao.getIstance().getDistributoreDao().get(1, Distributore.class));
		distributores.add(FactoryDao.getIstance().getDistributoreDao().get(3, Distributore.class));
		
		Dipendente dipendente = new Dipendente(FactoryDao.getIstance().getPersonaDao().get(1, Persona.class), 
				"0888445566", rifornisces, manutienes, distributores);
		
		assertNotNull(dipendente);
		assertTrue(dipendente.getRifornisces().size()==2);
		assertTrue(dipendente.getDistributores().size()==2);
		assertTrue(dipendente.getManutienes().size()==1);
		assertEquals("0888445566",dipendente.getTelefono());
		assertEquals("Giovanni", dipendente.getPersona().getNome());
	}

}
