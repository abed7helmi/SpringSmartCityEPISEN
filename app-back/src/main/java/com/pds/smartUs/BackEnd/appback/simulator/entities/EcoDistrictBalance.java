package com.pds.smartUs.BackEnd.appback.simulator.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy.CityHouse;
import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy.EcoDistrict;
import org.springframework.boot.jackson.JsonComponent;

import java.util.ArrayList;
import java.util.List;

@JsonComponent
public class EcoDistrictBalance {
    @JsonProperty(value = "district")
    private EcoDistrict ecoDistrict;

    @JsonProperty(value = "amountProduced")
    private double amountProduced;

    @JsonProperty(value = "amountConsumed")
    private double amountConsumed;

    @JsonProperty(value = "houses")
    private List<HouseBalance> housesBalance = new ArrayList<>();

    @JsonProperty(value = "buildings")
    private List<BuildingBalance> buildingsBalance = new ArrayList<>();

    public EcoDistrictBalance() {
    }

    public EcoDistrictBalance(EcoDistrict ecoDistrict, double amountProduced, double amountConsumed) {
        this.ecoDistrict = ecoDistrict;
        this.amountProduced = amountProduced;
        this.amountConsumed = amountConsumed;
    }

    public EcoDistrictBalance(EcoDistrict ecoDistrict, double amountProduced, double amountConsumed,
                              List<HouseBalance> housesBalance, List<BuildingBalance> buildingsBalance) {
        this.ecoDistrict = ecoDistrict;
        this.amountProduced = amountProduced;
        this.amountConsumed = amountConsumed;
        this.housesBalance = housesBalance;
        this.buildingsBalance = buildingsBalance;
    }

    public EcoDistrict getEcoDistrict() {
        return ecoDistrict;
    }

    public void setEcoDistrict(EcoDistrict ecoDistrict) {
        this.ecoDistrict = ecoDistrict;
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

    public List<HouseBalance> getHousesBalance() {
        return housesBalance;
    }

    public void setHousesBalance(List<HouseBalance> housesBalance) {
        this.housesBalance = housesBalance;
    }

    public List<BuildingBalance> getBuildingsBalance() {
        return buildingsBalance;
    }

    public void setBuildingsBalance(List<BuildingBalance> buildingsBalance) {
        this.buildingsBalance = buildingsBalance;
    }

    public void addHouseBalance(HouseBalance houseBalance){
        housesBalance.add(houseBalance);
    }

    public void addBuildingBalance(BuildingBalance buildingBalance){
        buildingsBalance.add(buildingBalance);
    }
}
