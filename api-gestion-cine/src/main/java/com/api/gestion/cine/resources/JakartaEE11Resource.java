package com.api.gestion.cine.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author
 */
@Path("ping")
public class JakartaEE11Resource {

    @GET
    public Response ping() {
        return Response
                .ok("ping Jakarta EE")
                .build();
    }
}
