package org.jboss.seam.compat.cdi.validation;

import java.io.File;

import javax.enterprise.event.Observes;

public class Observer {
    public void observe(@Observes String event, File file) {
    }
}
