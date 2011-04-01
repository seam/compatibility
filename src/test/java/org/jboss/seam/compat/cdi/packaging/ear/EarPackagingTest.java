package org.jboss.seam.compat.cdi.packaging.ear;

import javax.enterprise.inject.spi.Extension;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.spec.servlet.web.WebAppDescriptor;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * This test verifies that a CDI extension can be used with a web application bundled inside of an enterprise archive (.ear)
 * 
 * @see https://issues.jboss.org/browse/JBAS-8683
 * @author <a href="http://community.jboss.org/people/jharting">Jozef Hartinger</a>
 * 
 */
@RunWith(Arquillian.class)
public class EarPackagingTest {

    @Deployment(testable = false)
    public static EnterpriseArchive createEar() {
        return ShrinkWrap.create(EnterpriseArchive.class, "test.ear")
                .setApplicationXML("org/jboss/seam/compat/cdi/packaging/ear/application.xml").addAsModule(createWar());
    }

    public static WebArchive createWar() {
        StringAsset webXml = new StringAsset(Descriptors.create(WebAppDescriptor.class).exportAsString());
        return ShrinkWrap.create(WebArchive.class, "test.war").setWebXML(webXml)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml").addAsLibrary(createJar());
    }

    public static JavaArchive createJar() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar").addClasses(SimpleExtension.class, SimpleInjectionPoint.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsServiceProvider(Extension.class, SimpleExtension.class);
    }

    @Test
    public void test() {
        // noop, just verify we can deploy the app
    }
}
