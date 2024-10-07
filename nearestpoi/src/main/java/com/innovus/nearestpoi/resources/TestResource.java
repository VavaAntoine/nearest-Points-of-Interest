
package com.innovus.nearestpoi.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/v1/test")
public class TestResource {

    @GET
    public Response test() {
        return Response.ok("Test successful!").build();
    }
}
