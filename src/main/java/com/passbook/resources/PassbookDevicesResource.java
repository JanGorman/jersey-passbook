package com.passbook.resources;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.passbook.api.Pass;
import com.passbook.api.PushToken;
import com.passbook.core.Device;
import com.passbook.core.Registration;
import com.passbook.db.DeviceDAO;
import com.passbook.db.RegistrationDAO;
import com.passbook.helper.Authenticator;

import javax.annotation.Nullable;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/v1/devices")
@Produces(MediaType.APPLICATION_JSON)
public class PassbookDevicesResource {

    private static final Function<Device, Pass> TRANSFORM = new Function<Device, Pass>() {
        @Override
        public Pass apply(@Nullable final Device device) {
            if (device != null) {
                return new Pass(device.getUpdatedAt(), device.getSerialNumber());
            }
            return null;
        }
    };

    private final DeviceDAO deviceDAO;

    private final RegistrationDAO registrationDAO;

    public PassbookDevicesResource(DeviceDAO deviceDAO, final RegistrationDAO registrationDAO) {
        this.deviceDAO = deviceDAO;
        this.registrationDAO = registrationDAO;
    }


    @GET
    @Path("{deviceLibraryIdentifier}/registrations/{passTypeIdentifier}/{updatedSince}")
    public List<Pass> getSerialNumbers(@PathParam("deviceLibraryIdentifier") String deviceLibraryIdentifier,
                                       @PathParam("passTypeIdentifier") String passTypeIdentifier,
                                       @PathParam("updatedSince") Optional<Long> updatedSince) {
        List<Device> result = deviceDAO.findByPassTypeIdentifierAndDeviceLibraryIdentifier(deviceLibraryIdentifier, passTypeIdentifier);
        if (result.isEmpty()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        List<Device> passes = Lists.newArrayList();
        if (updatedSince.isPresent()) {
            for (Device device : result) {
                if (device.getUpdatedAt() > updatedSince.get()) {
                    passes.add(device);
                }
            }
        } else {
            passes.addAll(result);
        }

        if (passes.isEmpty()) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }

        return Lists.transform(passes, TRANSFORM);
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

        if (!Authenticator.isAuthorized(headers, device.get())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        // Already registered?
        for (Registration registration : device.get().getRegistrations()) {
            if (pushToken.getPushToken().equals(registration.getPushToken())) {
                return Response.ok().build();
            }
        }

        Registration registration = new Registration();
        registration.setCreatedAt(System.currentTimeMillis());
        registration.setUpdatedAt(System.currentTimeMillis());
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

        if (!Authenticator.isAuthorized(headers, device.get())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Optional<Registration> registration = Optional.absent();
        for (Registration item : device.get().getRegistrations()) {
            if (deviceLibraryIdentifier.equals(item.getDeviceLibraryIdentifier())) {
                registration = Optional.of(item);
                break;
            }
        }

        if (!registration.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        registrationDAO.destroy(registration.get());

        return Response.ok().build();
    }

}
