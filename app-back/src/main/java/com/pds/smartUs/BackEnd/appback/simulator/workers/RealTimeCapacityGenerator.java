package com.pds.smartUs.BackEnd.appback.simulator.workers;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.ProductionSiteModel;
import com.pds.smartUs.BackEnd.appback.services.smartgrid.ProductionSiteService;
import com.pds.smartUs.BackEnd.appback.simulator.config.SmartGridVariables;
import com.pds.smartUs.BackEnd.appback.simulator.entities.SimulatorParameters;
import com.pds.smartUs.BackEnd.appback.simulator.entities.SimulatorResponse;
import com.pds.smartUs.BackEnd.appback.simulator.entities.SiteCapacitiesResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RealTimeCapacityGenerator implements Runnable {

    private double totalSitesCapacity;
    private SimulatorResponse response;
    private final ProductionSiteService productionSiteService;
    private Iterable<ProductionSiteModel> productionSites;
    private final SimulatorRequester simulatorRequester;
    private List<SiteCapacitiesResponse> siteCapacities = new ArrayList<>();

    public RealTimeCapacityGenerator(ProductionSiteService productionSiteService,
                                     SimulatorRequester simulatorRequester) {
        this.productionSiteService = productionSiteService;
        this.simulatorRequester = simulatorRequester;
    }

    @Override
    public void run() {
        // Change thread name for the logger
        Thread.currentThread().setName("[SmartGrid-Thread] Capacities Generator Thread");

        // Get All Production Sites
        this.productionSites = productionSiteService.getProductionSite();

        while (true) {
            try{
                totalSitesCapacity = 0.0;
                // Getting params from simulator
                SimulatorResponse simulatorParams = simulatorRequester.getSimulatorResponse();
                this.setParamsResponse(simulatorParams);
                // Capacities from params
                setSitesCapacities();
                Thread.sleep(500);
            }
            catch (InterruptedException e ) {
                e.printStackTrace();
            }
        }
    }

    public List<SiteCapacitiesResponse> getSiteCapacities() {
        return siteCapacities;
    }

    public double getSitesTotalCapacity() {
        return totalSitesCapacity;
    }

    public void setSitesCapacities() {
        siteCapacities = new ArrayList<>();
        SimulatorParameters parameters = response.getParameters();
        productionSites.forEach(site -> {
            SiteCapacitiesResponse capacity = new SiteCapacitiesResponse();
            capacity.setProductionSite(site);
            switch (site.getEnergyCaracs().getEnergytype()) {
                case SOLAR -> capacity.setCapacity(determineSolarAmount(parameters.getTemperature(),
                        parameters.getCloudCover()));
                case WIND -> capacity.setCapacity(determineWindCapacity(parameters.getWindSpeed()));
                case HYDRAULIC -> capacity.setCapacity(determineHydraulicAmount(parameters.getPrecipitation()));
                case THERMAL -> capacity.setCapacity(determineThermalAmount(parameters.getTemperature()));
            }
            totalSitesCapacity += capacity.getCapacity();
            siteCapacities.add(capacity);
        });
    }

    public void setParamsResponse(SimulatorResponse response) {
        this.response = response;
    }

    //Simulators
    private double determineWindCapacity(float windSpeed) {
        return Math.abs(windSpeed) * 1.43;
    }

    private double determineSolarAmount(float temperature, float cloudPercentage) {
        if(temperature < 0) temperature = 0;
        return temperature * 1.1875 + (100 - cloudPercentage) * 1.1875;
    }

    private double determineHydraulicAmount(float rainPercentage) {
        return Math.abs(rainPercentage) * 0.96;
    }

    private double determineThermalAmount(float temperature) {
        if(temperature < 0) temperature = 0;
        return temperature * 0.48;
    }

}
