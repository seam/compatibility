package org.jboss.seam.compat.jaxrs.validation;

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
import org.jboss.shrinkwrap.descriptor.api.spec.cdi.beans.BeansDescriptor;
import org.jboss.shrinkwrap.descriptor.api.spec.servlet.web.WebAppDescriptor;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class InterceptedResourceTest extends AbstractRestClientTest {
    @ArquillianResource URL deploymentUrl;
    
    @Deployment(testable = false)
    public static WebArchive getDeployment() {
        BeansDescriptor beansXml = Descriptors.create(BeansDescriptor.class).interceptor(ValidationInterceptor.class);
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
