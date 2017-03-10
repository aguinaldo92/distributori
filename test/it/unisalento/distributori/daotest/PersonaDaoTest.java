package it.unisalento.distributori.daotest;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.opensymphony.xwork2.interceptor.annotations.Before;
import com.sun.istack.internal.NotNull;

import it.unisalento.distributori.dao.PersonaDao;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;


public class PersonaDaoTest {

	@BeforeClass
	public static void create(){
		System.out.println("Before Class");
	}
	
	@Before
	public void before(){
		System.out.println("Before");
	}
	
	@Test
	public void testSet() throws SQLException {
		PersonaDao dao= FactoryDao.getIstance().getPersonaDao();
		Persona persona=new Persona();
		persona.setNome("Giacomo");
		persona.setPassword("password");
		persona.setCognome("Rossi");
		persona.setEmail("dd@ff.it");
		
		persona.setId(dao.set(persona));
		assertTrue(persona.getId()>0);
	}
	
	@Test
	public void testGetById() throws Exception{
		
		PersonaDao dao=FactoryDao.getIstance().getPersonaDao();
		Persona persona=dao.get(1, Persona.class);
		
		assertNotNull(persona);
		assertEquals((Integer)1, persona.getId());
	}
	
}
