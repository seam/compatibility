package org.jboss.seam.compat.interceptor;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.spec.cdi.beans.BeansDescriptor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * This test checks that an interceptor enabled in beans.xml
 * properly intercepts a method to which it is bound.
 * 
 * @author Dan Allen
 */
@RunWith(Arquillian.class)
public class EnableInterceptorTest {
    @Deployment
    public static WebArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
            .addClasses(Bit.class, FlipBit.class, FlipBitInterceptor.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        
        BeansDescriptor beansXml = Descriptors.create(BeansDescriptor.class).interceptor(FlipBitInterceptor.class);
        return ShrinkWrap.create(WebArchive.class, "test.war")
            .addClasses(Bean.class)
            .addAsLibrary(jar)
            .addAsWebInfResource(new StringAsset(beansXml.exportAsString()), beansXml.getDescriptorName());
    }
    
    @Test
    public void shouldInterceptBeanMethod(Bean bean, Bit bit) {
        bean.operation();
        Assert.assertTrue("Method was not intercepted", bit.isFlipped());
    }
}
