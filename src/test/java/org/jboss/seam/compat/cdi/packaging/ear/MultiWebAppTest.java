package org.jboss.seam.compat.cdi.packaging.ear;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.junit.runner.RunWith;

/**
 * This test verifies that multiple CDI-enabled web applications can be bundled inside of an enterprise archive (.ear)
 *
 * @see http://java.net/jira/browse/GLASSFISH-16303
 * @author <a href="http://community.jboss.org/people/jharting">Jozef Hartinger</a>
 * 
 */
@RunWith(Arquillian.class)
public class MultiWebAppTest  extends SingleWebAppTest{

    @Deployment(testable = false)
    public static EnterpriseArchive createEar() {
        return ShrinkWrap.create(EnterpriseArchive.class, "test.ear")
                .setApplicationXML("org/jboss/seam/compat/cdi/packaging/ear/multi-application.xml")
                .addAsModule(createWar("foo.war", createJar("foo.jar", FooExtension.class, FooBean.class)))
                .addAsModule(createWar("bar.war", createJar("boo.jar", BarExtension.class, BarBean.class)));
    }
}
