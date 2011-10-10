package org.jboss.seam.compat.ejb.deployment;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.webapp30.WebAppDescriptor;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class DeploymentOrderTest {
    @Deployment
    public static WebArchive getDeployment() {
        WebAppDescriptor webXml = Descriptors.create(WebAppDescriptor.class);
        return ShrinkWrap.create(WebArchive.class, "test.war").addClasses(Filter.class, Foxtrot.class, Echo.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml").setWebXML(new StringAsset(webXml.exportAsString()));
    }

    @Test
    public void test() {
        // noop
        // we are interested in whether the app deploys
    }
}
