package com.pds.smartUs.BackEnd.appback.simulator.powersites;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pds.smartUs.BackEnd.appback.simulator.enums.EnergyType;
import com.pds.smartUs.BackEnd.appback.simulator.powerproduction.EnergyProduction;
import com.pds.smartUs.BackEnd.appback.simulator.powerproduction.EnergyProductionRequest;
import org.springframework.stereotype.Service;

@Service
public class GeothermalPowerSite extends ProductionSite{

    @JsonProperty("site_delay")
    public final long GEOTHERMAL_SITE_PRODUCTION_DELAY = 4800 * 1000; // 1.5 Hour

    public GeothermalPowerSite() {
        this.energyProduction = new EnergyProduction();
    }

    @Override
    public void produce(EnergyProductionRequest productionRequest) {
        checkAndSaveProduction(productionRequest);
    }

    @Override
    public EnergyType getEnergyType() {
        return EnergyType.GEOTHERMAL;
    }

    @Override
    public float getMaxCapacity() {
        return 27302;
    }
}
