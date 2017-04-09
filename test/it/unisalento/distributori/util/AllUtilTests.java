package it.unisalento.distributori.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GeneraPwdTest.class, PermissionsHashMapTest.class, SendMailSSLTest.class, ImageModifierTest.class })
public class AllUtilTests {

}
