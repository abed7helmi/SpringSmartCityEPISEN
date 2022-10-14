package com.pds.smartUs.BackEnd.appback.simulator.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy.CityHouse;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class HouseBalance {

    @JsonProperty(value = "house")
    private CityHouse house;

    @JsonProperty(value = "amountConsumed")
    private double amountConsumed;

    public HouseBalance() {
    }

    public HouseBalance(CityHouse house, double amountProduced, double amountConsumed) {
        this.house = house;
        this.amountConsumed = amountConsumed;
    }

    public CityHouse getHouse() {
        return house;
    }

    public void setHouse(CityHouse house) {
        this.house = house;
    }

    public double getAmountConsumed() {
        return amountConsumed;
    }

    public void setAmountConsumed(double amountConsumed) {
        this.amountConsumed = amountConsumed;
    }
}
