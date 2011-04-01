package org.jboss.seam.compat.cdi.packaging.ear;

import javax.inject.Inject;

public class FooBean {

    @Inject
    private FooExtension extension;

    public FooExtension getExtension() {
        return extension;
    }
}
