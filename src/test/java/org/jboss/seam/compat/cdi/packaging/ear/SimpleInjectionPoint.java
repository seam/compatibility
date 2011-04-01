package org.jboss.seam.compat.cdi.packaging.ear;

import javax.inject.Inject;

public class SimpleInjectionPoint {

    @Inject
    private SimpleExtension extension;

    public SimpleExtension getExtension() {
        return extension;
    }
}
