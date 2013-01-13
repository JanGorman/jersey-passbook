package com.passbook.resources;

import com.google.common.base.Optional;
import com.passbook.core.Device;
import com.passbook.db.DeviceDAO;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/devices")
@Produces(MediaType.APPLICATION_JSON)
public class PassbookDevicesResource {

    private final DeviceDAO deviceDAO;

    public PassbookDevicesResource(DeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    @POST
    @Path("{deviceLibraryIdentifier}/registrations/{passTypeIdentifier}/{serialNumber}")
    public Response registerDevice(@PathParam("deviceLibraryIdentifier") String deviceLibraryIdentifier,
                                   @PathParam("passTypeIdentifier") String passTypeIdentifier,
                                   @PathParam("serialNumber") String serialNumber) {
        Optional<Device> device = deviceDAO.findByPassTypeIdentifierAndSerialNumber(passTypeIdentifier, serialNumber);
        if (!device.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return null;
    }

}
