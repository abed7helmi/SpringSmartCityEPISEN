package com.pds.smartUs.BackEnd.appback.simulator.powersites;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pds.smartUs.BackEnd.appback.simulator.enums.EnergyType;
import com.pds.smartUs.BackEnd.appback.simulator.config.SmartGridVariables;
import com.pds.smartUs.BackEnd.appback.simulator.powerproduction.EnergyProduction;
import com.pds.smartUs.BackEnd.appback.simulator.powerproduction.EnergyProductionRequest;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Service
public class SolarPowerSite extends ProductionSite {

    @JsonProperty("site_delay")
    public final long SOLAR_SITE_PRODUCTION_DELAY = 3600 * 1000; // 1 Hour

    private final BlockingQueue<EnergyProductionRequest> productionRequests = new ArrayBlockingQueue<>((int)
            (SOLAR_SITE_PRODUCTION_DELAY / SmartGridVariables.SIMULATOR_REFRESH_FREQUENCY));

    public SolarPowerSite() {
        this.energyProduction = new EnergyProduction();
    }

    @Override
    public void produce(EnergyProductionRequest productionRequest) {
        /*if(productionRequests.isEmpty()) isProducing = false;
        else if(productionRequests.remainingCapacity() == 0) isProducing = true;
        try {
            if(isProducing) checkAndSaveProduction(productionRequests.take());
            productionRequests.put(productionRequest);
        }
        catch (InterruptedException e) {e.printStackTrace();}*/
        checkAndSaveProduction(productionRequest);
    }

    @Override
    public EnergyType getEnergyType() {
        return EnergyType.SOLAR;
    }

    @Override
    public float getMaxCapacity() {
        return 50000;
    }
}
