package org.jboss.seam.compat.scanning;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author <a href="http://community.jboss.org/people/jharting">Jozef Hartinger</a>
 * @see http://java.net/jira/browse/GLASSFISH-16318
 */
@RunWith(Arquillian.class)
public class ExtendedClassTest {

    @Deployment(testable = false)
    public static WebArchive getDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addClass(OptionalService2.class);
    }

    @Test
    public void test() {
        // noop - just verify the deployment succeeds
    }
}
