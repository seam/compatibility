package org.jboss.seam.compat.jaxrs.interceptor;

import org.jboss.arquillian.container.test.api.Deployment;
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
import org.jboss.shrinkwrap.descriptor.api.beans10.BeansDescriptor;
import org.jboss.shrinkwrap.descriptor.api.webapp30.WebAppDescriptor;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class InterceptedResourceTest extends AbstractRestClientTest {
    // ARQ-504
    // @ArquillianResource
    // URL deploymentUrl;

    private String deploymentUrl = "http://localhost:8080/test/";

    @Deployment(testable = false)
    public static WebArchive getDeployment() {
        BeansDescriptor beansXml = Descriptors.create(BeansDescriptor.class).createInterceptors().clazz(ValidationInterceptor.class.getName()).up();
        WebAppDescriptor webXml = Descriptors.create(WebAppDescriptor.class);
        return ShrinkWrap.create(WebArchive.class, "test.war").addClasses(MyApplication.class, Resource.class)
                .addAsWebInfResource(new StringAsset(beansXml.exportAsString()), beansXml.getDescriptorName())
                .setWebXML(new StringAsset(webXml.exportAsString())).addAsLibrary(getJar());
    }

    public static JavaArchive getJar() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar").addClasses(Valid.class, ValidationInterceptor.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testCdiResourceIsIntercepted() throws Exception {
        test(deploymentUrl.toString() + "api/test/ping", 200, "Validated pong");
    }
}
