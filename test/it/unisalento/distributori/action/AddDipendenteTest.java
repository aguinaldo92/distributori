package it.unisalento.distributori.action;

import org.apache.struts2.StrutsTestCase;

import com.mockobjects.servlet.MockHttpServletRequest;

import it.unisalento.distributori.model.PersonaModel;

public class AddDipendenteTest extends StrutsTestCase{
	
	public void testFormErrorMessage(){
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		PersonaModel DipFormTest = new PersonaModel();
		
		DipFormTest.setCognome("Cognome JUnit");
		DipFormTest.setEmail("test@junit.it");
		DipFormTest.setNome("Nome JUnit");
		DipFormTest.setTelefono("0000778899");
		
		request.setAttribute("DipForm", DipFormTest);
		
	}

}
