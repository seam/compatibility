package org.jboss.seam.compat.interceptor;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
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
            .addManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        
        return ShrinkWrap.create(WebArchive.class, "test.war")
            .addClasses(Bean.class)
            .addLibrary(jar)
            .addWebResource(EnableInterceptorTest.class.getPackage(), "EnableInterceptorTest-beans.xml", "beans.xml");
    }
    
    @Test
    public void shouldInterceptBeanMethod(Bean bean, Bit bit) {
        bean.operation();
        Assert.assertTrue("Method was not intercepted", bit.isFlipped());
    }
}
