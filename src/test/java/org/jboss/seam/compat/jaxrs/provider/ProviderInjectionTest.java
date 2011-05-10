package org.jboss.seam.compat.jaxrs.provider;

import java.net.URL;

import org.jboss.arquillian.api.ArquillianResource;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.compat.jaxrs.AbstractRestClientTest;
import org.jboss.seam.compat.jaxrs.MyApplication;
import org.jboss.seam.compat.jaxrs.Resource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.spec.servlet.web.WebAppDescriptor;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Verifies that a JAX-RS provider gets JAX-RS injections when bundled inside a .jar placed in WEB-INF/lib.
 *
 * @author <a href="http://community.jboss.org/people/jharting">Jozef Hartinger</a>
 * @see http://java.net/jira/browse/GLASSFISH-15794
 * @see https://issues.jboss.org/browse/RESTEASY-506
 */
@RunWith(Arquillian.class)
public class ProviderInjectionTest extends AbstractRestClientTest {
    @ArquillianResource
    URL deploymentUrl;

    @Deployment(testable = false)
    public static WebArchive getDeployment() {
        WebAppDescriptor webXml = Descriptors.create(WebAppDescriptor.class);
        return ShrinkWrap.create(WebArchive.class, "test.war").addClasses(MyApplication.class, Resource.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml").setWebXML(new StringAsset(webXml.exportAsString()))
                .addAsLibrary(getJar());
    }

    public static JavaArchive getJar() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar").addClasses(MyExceptionMapper.class, Foo.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testProviderInjected() throws Exception {
        test(deploymentUrl.toString() + "api/test/exception", 200,
                "SecurityContext:true,Providers:true,ServletConfig:true,ServletContext:true,CDI field injection:true");
    }
}
