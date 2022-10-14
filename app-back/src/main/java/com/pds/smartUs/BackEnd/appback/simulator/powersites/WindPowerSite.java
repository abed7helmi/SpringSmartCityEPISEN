package com.pds.smartUs.BackEnd.appback.simulator.powersites;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pds.smartUs.BackEnd.appback.simulator.enums.EnergyType;
import com.pds.smartUs.BackEnd.appback.simulator.powerproduction.EnergyProduction;
import com.pds.smartUs.BackEnd.appback.simulator.powerproduction.EnergyProductionRequest;
import org.springframework.stereotype.Service;

@Service
public class WindPowerSite extends ProductionSite {

    @JsonProperty("site_delay")
    public final long WIND_SITE_PRODUCTION_DELAY = 3600 * 2000; // 2 Hours

    public WindPowerSite() {
        this.energyProduction = new EnergyProduction();
    }

    @Override
    public void produce(EnergyProductionRequest productionRequest) {
        checkAndSaveProduction(productionRequest);
    }

    @Override
    public EnergyType getEnergyType() {
        return EnergyType.WIND;
    }

    @Override
    public float getMaxCapacity() {
        return 49076;
    }
}
