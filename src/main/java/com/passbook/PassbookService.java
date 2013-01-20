package com.passbook;

import com.passbook.db.DeviceDAO;
import com.passbook.db.RegistrationDAO;
import com.passbook.resources.PassbookDevicesResource;
import com.passbook.resources.PassbookPassesResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.jdbi.DBIFactory;
import org.skife.jdbi.v2.DBI;

public class PassbookService extends Service<PassbookConfiguration> {

    public static void main(String[] args) throws Exception {
        new PassbookService().run(args);
    }

    @Override
    public void initialize(Bootstrap<PassbookConfiguration> bootstrap) {
    }

    @Override
    public void run(PassbookConfiguration configuration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDatabase(), "postgresql");
        final DeviceDAO deviceDAO = jdbi.onDemand(DeviceDAO.class);
        final RegistrationDAO registrationDAO = jdbi.onDemand(RegistrationDAO.class);

        environment.addResource(new PassbookDevicesResource(deviceDAO, registrationDAO));
        environment.addResource(new PassbookPassesResource(deviceDAO));
    }
}
