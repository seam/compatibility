package org.jboss.seam.compat.cdi.packaging.ear;

import javax.inject.Inject;

public class BarBean {

    @Inject
    private BarExtension extension;

    public BarExtension getExtension() {
        return extension;
    }
}
