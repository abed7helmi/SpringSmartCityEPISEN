package com.pds.smartUs.BackEnd.appback.simulator.powersites;

import com.pds.smartUs.BackEnd.appback.simulator.enums.EnergyType;
import com.pds.smartUs.BackEnd.appback.simulator.powerproduction.EnergyProduction;
import com.pds.smartUs.BackEnd.appback.simulator.powerproduction.EnergyProductionRequest;

public class FossilFuelSite extends ProductionSite {

    FossilFuelSite() {
        this.energyProduction = new EnergyProduction();
    }

    @Override
    public void produce(EnergyProductionRequest productionRequest) {}

    @Override
    public EnergyType getEnergyType() {
        return EnergyType.FOSSIL;
    }

    @Override
    public float getMaxCapacity() {
        return Float.MAX_VALUE;
    }
}
