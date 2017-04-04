package it.unisalento.distributori.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DettaglioDistributoreModelTest.class, DistributoreModelTest.class, PersonaModelTest.class,
		ProdottoModelTest.class })
public class AllModelTests {

}
