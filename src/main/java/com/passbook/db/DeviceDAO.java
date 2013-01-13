package com.passbook.db;

import com.google.common.base.Optional;
import com.passbook.core.Device;
import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class DeviceDAO extends AbstractDAO<Device> {

    public DeviceDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Device> findByPassTypeIdentifierAndSerialNumber(String passTypeIdentifier, String serialNumber) {
        // Dodgy
        return (Optional<Device>) Optional.fromNullable(namedQuery("com.passbook.core.Device.findByPassTypeIdentifierAndSerialNumber")
                .setString("passTypeIdentifier", passTypeIdentifier).setString("serialNumber", serialNumber).uniqueResult());
    }

}
