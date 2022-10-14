package com.pds.smartUs.BackEnd.appback.simulator.powerproduction;

import com.pds.smartUs.BackEnd.appback.simulator.enums.EnergyType;

public class EnergyProductionRequest {

    private static int nbRequests = 0;
    private float amountToProduce;
    private boolean isValid = true;
    private EnergyType energyType;

   public EnergyProductionRequest() {
       nbRequests++;
   }

    public void setAmountToProduce(float amountToProduce) {
        this.amountToProduce = amountToProduce;
    }

    public float getAmountToProduce() {
        return amountToProduce;
    }

    public void validateRequest() {
       isValid = true;
    }

    public void rejectRequest() {
       isValid = false;
    }

    public boolean isValid() {
        return isValid;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }
}
