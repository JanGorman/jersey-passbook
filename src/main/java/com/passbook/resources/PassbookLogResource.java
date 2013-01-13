package com.passbook.resources;

import com.passbook.api.LogEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/log")
@Produces(MediaType.APPLICATION_JSON)
public class PassbookLogResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PassbookLogResource.class);

    @POST
    public Response log(@Valid LogEntry logEntry) {
        LOGGER.debug("Log {}", logEntry);
        return Response.ok().build();
    }

}
