package com.passbook.db;

import com.passbook.core.Registration;
import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class RegistrationDAO extends AbstractDAO<Registration> {

    public RegistrationDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Registration create(Registration registration) {
        return persist(registration);
    }

    public void destroy(Registration registration) {
        namedQuery("com.passbook.core.Registration.destroy")
                .setString("deviceLibraryIdentifier", registration.getDeviceLibraryIdentifier())
                .executeUpdate();
    }

}
