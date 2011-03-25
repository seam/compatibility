package org.jboss.seam.compat.ejb.deployment;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/*")
public class Filter implements javax.servlet.Filter {

    @Inject
    private Echo echo;
    @Inject
    private Foxtrot foxtrot;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        echo.ping();
        foxtrot.ping();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
