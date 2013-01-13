package com.passbook.resources;

import com.passbook.api.Log;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/log")
@Produces(MediaType.APPLICATION_JSON)
public class PassbookLogResource {

    @POST
    public Response log(@Valid Log log) {
        // Where to log?
        return Response.ok().build();
    }

}
