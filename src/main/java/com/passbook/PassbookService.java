package com.passbook;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class PassbookService extends Service<PassbookConfiguration> {

    public static void main(String[] args) throws Exception {
        new PassbookService().run(args);
    }

    @Override
    public void initialize(Bootstrap<PassbookConfiguration> bootstrap) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void run(PassbookConfiguration configuration, Environment environment) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
