package com.passbook.db;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.passbook.core.Device;
import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class DeviceDAO extends AbstractDAO<Device> {

    public DeviceDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Device> findByPassTypeIdentifierAndSerialNumber(String passTypeIdentifier, String serialNumber) {
        // Dodgy
        return (Optional<Device>) Optional.fromNullable(namedQuery("com.passbook.core.Device.findByPassTypeIdentifierAndSerialNumber")
                .setString("passTypeIdentifier", passTypeIdentifier).setString("serialNumber", serialNumber).uniqueResult());
    }

    public List<Device> findByPassTypeIdentifierAndDeviceLibraryIdentifier(String passTypeIdentifier, String deviceLibraryIdentifier) {
        return namedQuery("com.passbook.core.Device.findByPassTypeIdentifierAndDeviceLibraryIdentifier")
                .setString("passTypeIdentifier", passTypeIdentifier)
                .setString("deviceLibraryIdentifier", deviceLibraryIdentifier)
                .list();
    }

}
