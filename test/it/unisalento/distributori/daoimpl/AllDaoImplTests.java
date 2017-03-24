package it.unisalento.distributori.daoimpl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CategoriaDaoImplTest.class, CategorieForniteDaoImplTest.class, DipendenteDaoImplTest.class,
		DistributoreDaoImplTest.class, FamigliaDaoImplTest.class, FamiglieProdottoDaoImplTest.class,
		FeedbackDaoImplTest.class, HibernateUtilTest.class, PersonaDaoImplTest.class, ProdottiErogatiDaoImplTest.class,
		ProdottoDaoImplTest.class, ProduttoreDaoImplTest.class, StabilimentoDaoImplTest.class })
public class AllDaoImplTests {

}
