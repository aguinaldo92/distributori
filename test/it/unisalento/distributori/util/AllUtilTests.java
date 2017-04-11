package it.unisalento.distributori.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddressTranslationTest.class, GeneraPwdTest.class, ImageModifierTest.class, PasswordUtilsTest.class,
		PermissionsHashMapTest.class, SendMailSSLTest.class })
public class AllUtilTests {

}
