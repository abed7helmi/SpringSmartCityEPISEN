package com.pds.smartUs.BackEnd.appback.simulator.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

import java.util.ArrayList;
import java.util.List;

@JsonComponent
public class CityBalanceResponse {

    @JsonProperty(value = "cityBalance")
    private double cityBalance;

    @JsonProperty(value = "districts")
    private List<EcoDistrictBalance> districtsBalances = new ArrayList<>();

    public CityBalanceResponse() {
    }

    public CityBalanceResponse(double cityBalance, List<EcoDistrictBalance> districtsBalances) {
        this.cityBalance = cityBalance;
        this.districtsBalances = districtsBalances;
    }

    public double getCityBalance() {
        return cityBalance;
    }

    public void setCityBalance(double cityBalance) {
        this.cityBalance = cityBalance;
    }

    public List<EcoDistrictBalance> getDistrictsBalances() {
        return districtsBalances;
    }

    public void setDistrictsBalances(List<EcoDistrictBalance> districtsBalances) {
        this.districtsBalances = districtsBalances;
    }

    public void addDistrictBalance(EcoDistrictBalance districtBalance) {
        districtsBalances.add(districtBalance);
    }
}
