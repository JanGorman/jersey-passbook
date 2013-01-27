package com.passbook.resources;

import com.google.common.base.Optional;
import com.passbook.core.Device;
import com.passbook.db.DeviceDAO;
import com.passbook.helper.Authenticator;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/passes")
@Produces(MediaType.APPLICATION_JSON)
public class PassbookPassesResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PassbookPassesResource.class);

    private final DeviceDAO deviceDAO;

    public PassbookPassesResource(final DeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    @GET
    @UnitOfWork
    @Path("/{passTypeIdentifier}/{serialNumber}")
    public Response getLatest(@Context HttpHeaders headers,
                              @PathParam("passTypeIdentifier") String passTypeIdentifier,
                              @PathParam("serialNumber") String serialNumber) {
        Optional<Device> device = deviceDAO.findByPassTypeIdentifierAndSerialNumber(passTypeIdentifier, serialNumber);
        if (!device.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (!Authenticator.isAuthorized(headers, device.get())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        return Response.ok(device.get().getData())
                .lastModified(new DateTime(device.get().getUpdatedAt(), DateTimeZone.UTC).toDate())
                .build();
    }

}
