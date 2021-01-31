package com.fundsaccess.services.blueprint.port.adapter.bootstrap;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controls all steps that are necessary during startup of the serivce.
 */
@Singleton
@Startup
public class BlueprintStartupBean {

    private static final Logger LOG = LoggerFactory.getLogger(BlueprintStartupBean.class);

    // please fill your name here
    private static final String applicantName = "ApplicantName";


    /**
     * Called by CDI after the instance creation.
     */
    @PostConstruct
    public void init() {

        LOG.info("Starting services :: assessment :: {}", applicantName);

        //serviceRegistryService.registerService();

        LOG.info("Started services :: assessment :: {}", applicantName);
    }

}
