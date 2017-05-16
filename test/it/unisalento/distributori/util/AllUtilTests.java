package it.unisalento.distributori.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddressTranslationTest.class, ImageModifierTest.class, PasswordUtilsTest.class,
		PermissionsHashMapTest.class, SendMailSSLTest.class, FCMSenderTest.class })
public class AllUtilTests {

}
