package com.pds.smartUs.BackEnd.appback.smartgridmix;

import java.util.HashMap;

public class MixResponse {

    private AmountToProduce windEnergyAmount;
    private AmountToProduce geothermalEnergyAmount;
    private AmountToProduce solarEnergyAmount;
    private AmountToProduce hydraulicEnergyAmount;
    private HashMap<String,Integer > energyPrioritization;

    public MixResponse(AmountToProduce windEnergyAmount, AmountToProduce solarEnergyAmount, AmountToProduce hydraulicEnergyAmount, AmountToProduce geothermalEnergyAmount) {
        this.windEnergyAmount = windEnergyAmount;
        this.geothermalEnergyAmount = geothermalEnergyAmount;
        this.solarEnergyAmount = solarEnergyAmount;
        this.hydraulicEnergyAmount = hydraulicEnergyAmount;
        this.energyPrioritization = energyPrioritization;
    }

    public MixResponse(){}

    public AmountToProduce getWindEnergyAmount() {
        return windEnergyAmount;
    }

    public AmountToProduce getGeothermalEnergyAmount() {
        return geothermalEnergyAmount;
    }

    public AmountToProduce getSolarEnergyAmount() {
        return solarEnergyAmount;
    }

    public AmountToProduce getHydraulicEnergyAmount() {
        return hydraulicEnergyAmount;
    }


    @Override
    public String toString() {
        return "MixResponse{" +
                "windEnergyAmount=" + windEnergyAmount +
                ", geothermalEnergyAmount=" + geothermalEnergyAmount +
                ", solarEnergyAmount=" + solarEnergyAmount +
                ", hydraulicEnergyAmount=" + hydraulicEnergyAmount +
                '}';
    }
}
