package com.passbook.db;

import com.google.common.base.Optional;
import com.passbook.core.Device;
import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class DeviceDAO extends AbstractDAO<Device> {

    public DeviceDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Device> findByPassTypeIdentifierAndSerialNumber(String passTypeIdentifier, String serialNumber) {
        return Optional.fromNullable(uniqueResult(namedQuery("com.passbook.core.Device.findByPassTypeIdentifierAndSerialNumber")
                .setString("passTypeIdentifier", passTypeIdentifier)
                .setString("serialNumber", serialNumber)));
    }

    public List<Device> findByPassTypeIdentifierAndDeviceLibraryIdentifier(String passTypeIdentifier, String deviceLibraryIdentifier) {
        return list(namedQuery("com.passbook.core.Device.findByPassTypeIdentifierAndDeviceLibraryIdentifier")
                .setString("passTypeIdentifier", passTypeIdentifier)
                .setString("deviceLibraryIdentifier", deviceLibraryIdentifier));
    }

}
