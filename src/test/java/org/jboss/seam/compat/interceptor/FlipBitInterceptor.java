package org.jboss.seam.compat.interceptor;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@FlipBit
@Interceptor
public class FlipBitInterceptor {
    @Inject
    private Bit bit;
    
    @AroundInvoke
    public Object intercept(final InvocationContext ctx) throws Exception
    {
        bit.flip();
        return ctx.proceed();
    }
}
