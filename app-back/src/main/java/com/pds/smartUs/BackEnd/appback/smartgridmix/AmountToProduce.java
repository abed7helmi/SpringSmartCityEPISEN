package com.pds.smartUs.BackEnd.appback.smartgridmix;

import com.pds.smartUs.BackEnd.appback.simulator.enums.EnergyType;

public class AmountToProduce {

    private EnergyType energyType;
    private float amount;

    public AmountToProduce(EnergyType energyType, float amount) {
        this.energyType = energyType;
        this.amount = amount;
    }

    public AmountToProduce(EnergyType energyType) {
        this.energyType = energyType;
        this.amount = 0;
    }

    public AmountToProduce() {
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public float getAmount() {
        return amount;
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "AmountToProduce{" +
                "energyType=" + energyType +
                ", amount=" + amount +
                '}';
    }


}

