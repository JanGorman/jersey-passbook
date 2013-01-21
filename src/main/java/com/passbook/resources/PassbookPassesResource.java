package com.passbook.resources;

import com.passbook.api.Pass;
import com.passbook.core.Device;
import com.passbook.db.DeviceDAO;
import com.passbook.helper.Authenticator;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

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

    private final DeviceDAO deviceDAO;

    public PassbookPassesResource(final DeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    @GET
    @Path("/{passTypeIdentifier}/{serialNumber}")
    public Response getLatest(@Context HttpHeaders headers,
                              @PathParam("passTypeIdentifier") String passTypeIdentifier,
                              @PathParam("serialNumber") String serialNumber) {
        Device device = deviceDAO.findByPassTypeIdentifierAndSerialNumber(passTypeIdentifier, serialNumber);
        if (device == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (!Authenticator.isAuthorized(headers, device)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        return Response.ok(new Pass(device.getUpdatedAt(), serialNumber))
                .lastModified(new DateTime(device.getUpdatedAt(), DateTimeZone.UTC).toDate())
                .build();
    }

}
