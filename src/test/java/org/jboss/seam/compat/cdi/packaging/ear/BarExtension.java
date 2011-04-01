package org.jboss.seam.compat.cdi.packaging.ear;

import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Extension;

public class BarExtension implements Extension {
    
    public void beforeBeanDiscovery(@Observes BeforeBeanDiscovery event)
    {
        System.out.println("bar");
    }
}
