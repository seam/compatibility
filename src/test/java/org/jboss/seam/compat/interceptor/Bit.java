package org.jboss.seam.compat.interceptor;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class Bit {
    private boolean state = false;
    
    public boolean isFlipped() {
        return state;
    }
    
    public void flip() {
        state = true;
    }
    
    public void reset() {
        state = false;
    }
}
