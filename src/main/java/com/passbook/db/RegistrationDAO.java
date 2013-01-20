package com.passbook.db;

import com.passbook.core.Registration;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface RegistrationDAO {

    @SqlUpdate("INSERT INTO passbook_registrations (device_library_identifier, push_token, created_at, updated_at) " +
            "VALUES (:r.deviceLibraryIdentifier, :r.pushToken, :r.createdAt, :r.updatedAt)")
    public void create(@BindBean("r") Registration registration);

    @SqlUpdate("DELETE FROM passbook_registrations r WHERE r.device_library_identifier = :deviceLibraryIdentifier")
    public void destroy(@Bind("deviceLibraryIdentifier") String deviceLibraryIdentifier);

}
