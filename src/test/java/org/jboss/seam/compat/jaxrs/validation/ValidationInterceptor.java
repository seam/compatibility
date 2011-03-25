package org.jboss.seam.compat.jaxrs.validation;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Valid
public class ValidationInterceptor {
    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        return "Validated " + ctx.proceed();
    }
}
