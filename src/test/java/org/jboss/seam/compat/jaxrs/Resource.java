package org.jboss.seam.compat.jaxrs;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/test")
@Produces("text/plain")
@RequestScoped
public class Resource {
    @GET
    @Path("/ping")
    public String ping() {
        return "pong";
    }

    @GET
    @Path("/exception")
    public void exception() {
        throw new NullPointerException();
    }
}
