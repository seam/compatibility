package org.jboss.seam.compat.cdi.packaging.ear;

import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Extension;

public class SimpleExtension implements Extension {
    
    public void beforeBeanDiscovery(@Observes BeforeBeanDiscovery event)
    {
    }
}
