package com.pds.smartUs.BackEnd.appback.simulator.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy.CityBuilding;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class BuildingBalance {

    @JsonProperty(value = "building")
    private CityBuilding building;

    @JsonProperty(value = "amountProduced")
    private double amountProduced;

    @JsonProperty(value = "amountConsumed")
    private double amountConsumed;

    public BuildingBalance() {
    }

    public BuildingBalance(CityBuilding building, double amountProduced, double amountConsumed) {
        this.building = building;
        this.amountProduced = amountProduced;
        this.amountConsumed = amountConsumed;
    }

    public CityBuilding getBuilding() {
        return building;
    }

    public void setBuilding(CityBuilding building) {
        this.building = building;
    }

    public double getAmountProduced() {
        return amountProduced;
    }

    public void setAmountProduced(double amountProduced) {
        this.amountProduced = amountProduced;
    }

    public double getAmountConsumed() {
        return amountConsumed;
    }

    public void setAmountConsumed(double amountConsumed) {
        this.amountConsumed = amountConsumed;
    }
}
