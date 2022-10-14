package com.pds.smartUs.BackEnd.appback.simulator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:modules.properties")
public class GlobalVariables {

    @Value("${simulator.api.host}")
    private static String SIMULATOR_HOST;

    @Value("${simulator.api.port}")
    private static int SIMULATOR_PORT;

    @Value("${simulator.api.endpoint.conditions}")
    private static String CONDITION_ENDPOINT;

    private static String constructURL(String host, int port, String endpoint, boolean ssl) {
        String s = (ssl) ? "s" : "";
        return "http"+s+"://"+host+":"+port+"/"+endpoint;
    }

    public static String getSimulatorUrl() {
        return constructURL(SIMULATOR_HOST, SIMULATOR_PORT, CONDITION_ENDPOINT, false);
    }
}
