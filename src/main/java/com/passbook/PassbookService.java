package com.passbook;

import com.passbook.db.DeviceDAO;
import com.passbook.db.RegistrationDAO;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;

public class PassbookService extends Service<PassbookConfiguration> {

    private final HibernateBundle<PassbookConfiguration> hibernate = new HibernateBundle<PassbookConfiguration>() {
        @Override
        public DatabaseConfiguration getDatabaseConfiguration(PassbookConfiguration configuration) {
            return configuration.getDatabase();
        }
    };

    public static void main(String[] args) throws Exception {
        new PassbookService().run(args);
    }

    @Override
    public void initialize(Bootstrap<PassbookConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(PassbookConfiguration configuration, Environment environment) throws Exception {
        final DeviceDAO deviceDAO = new DeviceDAO(hibernate.getSessionFactory());
        final RegistrationDAO registrationDAO = new RegistrationDAO(hibernate.getSessionFactory());
        environment.addResource(deviceDAO);
        environment.addResource(registrationDAO);
    }
}
