package com.passbook.db;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface RegistrationDAO {

    @SqlUpdate("INSERT INTO passbook_registrations (device_library_identifier, push_token, created_at, updated_at) " +
            "VALUES (:deviceLibraryIdentifier, :pushToken, :createdAt, :updatedAt)")
    public void create(@Bind("deviceLibraryIdentifier") String deviceLibraryIdentifier,
                       @Bind("pushToken") String pushToken,
                       @Bind("createdAt") long createdAt,
                       @Bind("updatedAt") long updatedAt);

    @SqlUpdate("DELETE FROM passbook_registrations r WHERE r.device_library_identifier = :deviceLibraryIdentifier")
    public void destroy(@Bind("deviceLibraryIdentifier") String deviceLibraryIdentifier);

}
