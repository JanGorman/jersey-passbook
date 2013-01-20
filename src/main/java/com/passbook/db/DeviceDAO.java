package com.passbook.db;

import com.passbook.core.Device;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

import java.util.List;

public interface DeviceDAO {

    @SqlQuery("SELECT d FROM passbook_devices d WHERE d.pass_type_identifier = :passTypeIdentifier AND d.serial_number = :serialNumber")
    public Device findByPassTypeIdentifierAndSerialNumber(@Bind("passTypeIdentifier") String passTypeIdentifier,
                                                          @Bind("serialNumber") String serialNumber);


    @SqlQuery("SELECT d FROM passbook_devices d WHERE d.pass_type_identifier = :passTypeIdentifier " +
            "JOIN passbook_registrations r ON d.id = r.devices_id AND r.device_library_identifier = :deviceLibraryIdentifier")
    public List<Device> findByPassTypeIdentifierAndDeviceLibraryIdentifier(@Bind("passTypeIdentifier") String passTypeIdentifier,
                                                                           @Bind("deviceLibraryIdentifier") String deviceLibraryIdentifier);

}
