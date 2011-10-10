package org.jboss.seam.compat.cdi.packaging.ear;

import javax.enterprise.inject.spi.Extension;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.webapp30.WebAppDescriptor;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * This test verifies that a CDI extension can be used with a web application bundled inside of an enterprise archive (.ear)
 *
 * @author <a href="http://community.jboss.org/people/jharting">Jozef Hartinger</a>
 * @see https://issues.jboss.org/browse/JBAS-8683
 */
@RunWith(Arquillian.class)
public class SingleWebAppTest {

    @Deployment(testable = false)
    public static EnterpriseArchive createEar() {
        return ShrinkWrap.create(EnterpriseArchive.class, "test.ear")
                .setApplicationXML("org/jboss/seam/compat/cdi/packaging/ear/single-application.xml")
                .addAsModule(createWar("test.war", createJar("test.jar", FooExtension.class, FooBean.class)));
    }

    public static WebArchive createWar(String name, JavaArchive... libraries) {
        StringAsset webXml = new StringAsset(Descriptors.create(WebAppDescriptor.class).exportAsString());
        return ShrinkWrap.create(WebArchive.class, name).setWebXML(webXml)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml").addAsLibraries(libraries);
    }

    public static JavaArchive createJar(String name, Class<? extends Extension> extension, Class<?>... classes) {
        return ShrinkWrap.create(JavaArchive.class, name).addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addClasses(classes).addClass(extension).addAsServiceProvider(Extension.class, extension);
    }

    @Test
    public void test() {
        // noop, just verify we can deploy the app
    }
}
