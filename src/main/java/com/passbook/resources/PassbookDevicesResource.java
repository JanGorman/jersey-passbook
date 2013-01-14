package com.passbook.resources;

import com.google.common.base.Optional;
import com.passbook.api.PushToken;
import com.passbook.core.Device;
import com.passbook.core.Registration;
import com.passbook.db.DeviceDAO;
import com.passbook.db.RegistrationDAO;

import javax.swing.plaf.multi.MultiViewportUI;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

@Path("/v1/devices")
@Produces(MediaType.APPLICATION_JSON)
public class PassbookDevicesResource {

    private static final String HTTP_AUTHORIZATION = "HTTP_AUTHORIZATION";
    private static final String TOKEN = "ApplePass %s";
    
    private final DeviceDAO deviceDAO;

    private final RegistrationDAO registrationDAO;

    public PassbookDevicesResource(DeviceDAO deviceDAO, final RegistrationDAO registrationDAO) {
        this.deviceDAO = deviceDAO;
        this.registrationDAO = registrationDAO;
    }

    @POST
    @Path("{deviceLibraryIdentifier}/registrations/{passTypeIdentifier}/{serialNumber}")
    public Response registerDevice(@Context HttpHeaders headers,
                                   @PathParam("deviceLibraryIdentifier") String deviceLibraryIdentifier,
                                   @PathParam("passTypeIdentifier") String passTypeIdentifier,
                                   @PathParam("serialNumber") String serialNumber,
                                   PushToken pushToken) {
        Optional<Device> device = deviceDAO.findByPassTypeIdentifierAndSerialNumber(passTypeIdentifier, serialNumber);
        if (!device.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (!isAuthorized(headers, device.get())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        // Already registered?
        for (Registration registration : device.get().getRegistrations()) {
            if (pushToken.getPushToken().equals(registration)) {
                return Response.ok().build();
            }
        }

        Registration registration = new Registration();
        registration.setCreatedAt(System.currentTimeMillis());
        registration.setDeviceLibraryIdentifier(deviceLibraryIdentifier);
        registration.setDevice(device.get());

        registrationDAO.create(registration);

        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("{deviceLibraryIdentifier}/registrations/{passTypeIdentifier}/{serialNumber}")
    public Response unregisterDevice(@Context HttpHeaders headers,
                                     @PathParam("deviceLibraryIdentifier") String deviceLibraryIdentifier,
                                     @PathParam("passTypeIdentifier") String passTypeIdentifier,
                                     @PathParam("serialNumber") String serialNumber) {
        Optional<Device> device = deviceDAO.findByPassTypeIdentifierAndSerialNumber(passTypeIdentifier, serialNumber);
        if (!device.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (!isAuthorized(headers, device.get())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }


        return null;
    }

    private boolean isAuthorized(HttpHeaders headers, Device device) {
        MultivaluedMap<String, String> headerParams = headers.getRequestHeaders();
        return String.format(TOKEN, device.getAuthenticationToken()).equals(headerParams.getFirst(HTTP_AUTHORIZATION));
    }

}
