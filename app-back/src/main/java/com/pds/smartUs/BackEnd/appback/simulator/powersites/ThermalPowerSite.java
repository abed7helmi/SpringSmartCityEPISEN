package com.pds.smartUs.BackEnd.appback.simulator.powersites;


import com.pds.smartUs.BackEnd.appback.simulator.enums.EnergyType;
import com.pds.smartUs.BackEnd.appback.simulator.powerproduction.EnergyProduction;
import com.pds.smartUs.BackEnd.appback.simulator.powerproduction.EnergyProductionRequest;
import org.springframework.stereotype.Service;

@Service
public class ThermalPowerSite extends ProductionSite{

    public ThermalPowerSite() {
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
