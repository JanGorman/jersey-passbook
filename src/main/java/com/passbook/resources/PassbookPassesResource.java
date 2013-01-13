package com.passbook.resources;

import com.passbook.db.RegistrationDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/passes")
@Produces(MediaType.APPLICATION_JSON)
public class PassbookPassesResource {

    private final RegistrationDAO registrationDAO;


    public PassbookPassesResource(RegistrationDAO registrationDAO) {
        this.registrationDAO = registrationDAO;
    }

    @GET
    @Path("/{passTypeIdentifier}/{serialNumber")
    public Response getLatest(@PathParam("passTypeIdentifier") String passTypeIdentifier,
                              @PathParam("serialNumber") String serialNumber) {


        return Response.ok().build();
    }

}
