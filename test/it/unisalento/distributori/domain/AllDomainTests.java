package it.unisalento.distributori.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AcquistaTest.class, CategoriaTest.class, CategorieForniteTest.class, DipendenteTest.class,
		DistributoreTest.class, FamigliaTest.class, FamiglieProdottoTest.class, FeedbackTest.class, ManutieneTest.class,
		PersonaTest.class, ProdottiErogatiTest.class, ProdottoTest.class, ProduttoreTest.class, RifornisceTest.class,
		StabilimentoTest.class })
public class AllDomainTests {

}
