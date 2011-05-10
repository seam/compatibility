package org.jboss.seam.compat.jaxrs.provider;

import java.net.URL;

import org.jboss.arquillian.api.ArquillianResource;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.compat.jaxrs.AbstractRestClientTest;
import org.jboss.seam.compat.jaxrs.MyApplication;
import org.jboss.seam.compat.jaxrs.Resource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.spec.servlet.web.WebAppDescriptor;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Verifies that an Application subclass can be injected into a JAX-RS provider.
 *
 * @author <a href="http://community.jboss.org/people/jharting">Jozef Hartinger</a>
 * @see https://issues.jboss.org/browse/RESTEASY-506
 */
@RunWith(Arquillian.class)
public class ApplicationInjectedIntoProviderTest extends AbstractRestClientTest {
    @ArquillianResource
    URL deploymentUrl;

    @Deployment(testable = false)
    public static WebArchive getDeployment() {
        WebAppDescriptor webXml = Descriptors.create(WebAppDescriptor.class);
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(MyApplication.class, Resource.class, ExceptionMapperWithApplicationInjection.class)
                .setWebXML(new StringAsset(webXml.exportAsString()));
    }

    @Test
    public void testProviderInjected() throws Exception {
        test(deploymentUrl.toString() + "api/test/exception", 200, "Application:true");
    }
}
