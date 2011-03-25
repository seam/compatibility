package org.jboss.seam.compat.ejb.deployment;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;

@Singleton
@Startup
@ApplicationScoped
public class Echo {
    public boolean ping() {
        return true;
    }
}
