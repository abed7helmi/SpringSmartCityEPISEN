package com.pds.smartUs.BackEnd.appback.controllers.smartgrid;

import com.pds.smartUs.BackEnd.appback.simulator.entities.*;
import com.pds.smartUs.BackEnd.appback.smartgridmix.SmartGridRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;
import com.pds.smartUs.BackEnd.appback.simulator.workers.SmartGrid;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/smartgrid")
public class SmartGridProdController {

    @Autowired
    private SmartGrid smartgrid;

    private final Logger logger = LoggerFactory.getLogger(SmartGridProdController.class);


    @GetMapping(value = "/energy-balance", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public String getCityEnergyBalance() {

        Calendar now = Calendar.getInstance();
        String time = String.format("%s:%s:%s", now.get(Calendar.HOUR),
                now.get(Calendar.MINUTE), now.get(Calendar.SECOND));

        return new GenericResponse(1000, GenericResponse.Status.success,
                "City balance has been retrieved successfully")
                .withCustomField("city-balance", smartgrid.getCityBalance())
                .withCustomField("update-time", time)
                .toString();
    }

    @GetMapping(value = "/sites/productions", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public List<SiteProductionResponse> getSiteProductions() {
        return smartgrid.getProductionResponse();
    }

    @GetMapping(value = "/sites/capacities", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public List<SiteCapacitiesResponse> getSitesCapacities() {
        return smartgrid.getSiteCapacities();
    }

    @GetMapping(value = "/city/balances", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public CityBalanceResponse getCityBalances() {
        return smartgrid.getCityBalances();
    }

    @GetMapping(value = "/mix-requests", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public List<SmartGridRequest> getMixRequests() {
        return smartgrid.getMixRequest();
    }

    @GetMapping(value = "/mock-parameters", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public SimulatorResponse getMockParameters() {
        return smartgrid.getSimulatorResponse();
    }

    @PostMapping(value = "/mock-parameters/post", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public void postMockParameters(HttpServletRequest request, @RequestBody SimulatorParameters parameters) {
        logger.info("[POST SmartGrid] external mock parameters have been received from the simulator : "
                +request.getRemoteAddr());
        smartgrid.generateCapacities();
    }

    @PostMapping(value = "/config/frequency-time", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public String postFrequencyTime(HttpServletRequest request, @RequestBody FrequencyTimeRequest requestBody) {
        logger.info("[POST SmartGrid] request to change time frequency has been received : "
                +request.getRemoteAddr());

        switch (requestBody.getTimeUnit()) {
            case "minutes" -> smartgrid.setRelatedTimeInMinutes(requestBody.getTimeFrequency());
            case "seconds" -> smartgrid.setRelatedTimeInSeconds(requestBody.getTimeFrequency());
        }

        logger.info(String.format("[POST SmartGrid] time frequency is now : %s seconds", smartgrid.getRelatedTime()));

        return new GenericResponse(1000, GenericResponse.Status.success,
                String.format("Time frequency has been changed successfully to : %s seconds", smartgrid.getRelatedTime()))
                .withCustomField("time-frequency", smartgrid.getRelatedTime())
                .withCustomField("time-unit", "seconds")
                .toString();
    }

    @GetMapping(value = "/config/frequency-time", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public String getFrequencyTime() {
        return new GenericResponse(1000, GenericResponse.Status.success, "successful request")
                .withCustomField("time-frequency", smartgrid.getRelatedTime())
                .withCustomField("time-unit", "seconds")
                .toString();
    }

}
