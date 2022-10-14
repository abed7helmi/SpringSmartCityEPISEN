package com.pds.smartUs.BackEnd.appback.simulator.workers;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy.*;
import com.pds.smartUs.BackEnd.appback.services.smartgrid.CityEnergyService;
import com.pds.smartUs.BackEnd.appback.simulator.config.SmartGridVariables;
import com.pds.smartUs.BackEnd.appback.simulator.entities.BuildingBalance;
import com.pds.smartUs.BackEnd.appback.simulator.entities.CityBalanceResponse;
import com.pds.smartUs.BackEnd.appback.simulator.entities.EcoDistrictBalance;
import com.pds.smartUs.BackEnd.appback.simulator.entities.HouseBalance;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class EnergyBalancer implements Runnable {

    private double energyBalance;
    private final CityEnergyService cityEnergyService;
    private CityBalanceResponse balanceResponse;


    EnergyBalancer(CityEnergyService cityEnergyService){
        this.cityEnergyService = cityEnergyService;
        this.balanceResponse = new CityBalanceResponse();
    }

    @Override
    public void run() {
        // change thread name for the logger
        Thread.currentThread().setName("[SmartGrid-Thread] Energy Balancer");

        while (true) {
            try {
                List<EcoDistrict> districts = cityEnergyService.getAllEcoDistricts();


                List<EcoDistrictBalance> ecoDistrictBalances = new ArrayList<>();
                AtomicReference<Double> totalCityProduction = new AtomicReference<>(0.0);
                AtomicReference<Double> totalCityConsumption = new AtomicReference<>(0.0);

                districts.forEach(district -> {
                    AtomicReference<Double> totalDistrictProduction = new AtomicReference<>(0.0);
                    AtomicReference<Double> totalDistrictConsumption = new AtomicReference<>(0.0);

                    EcoDistrictBalance districtBalance = new EcoDistrictBalance();
                    List<CityHouse> districtHouses = cityEnergyService.getCityHousesByDistrictId(district.getId());
                    List<CityBuilding> districtBuildings = cityEnergyService.getCityBuildingsByDistrictId(
                            district.getId());

                    // for houses => get current consumptions
                    districtHouses.forEach(house -> {
                        HouseBalance houseBalance = new HouseBalance();
                        List<CurrentConsumption> consumptions = cityEnergyService.getCurrentConsumptionsById(
                                house.getId());
                        double totalHouseConsumption =
                                consumptions.stream().mapToDouble(CurrentConsumption::getAmount).sum();
                        houseBalance.setHouse(house);
                        houseBalance.setAmountConsumed(totalHouseConsumption);
                        districtBalance.addHouseBalance(houseBalance);
                        totalDistrictConsumption.updateAndGet(v -> (v + totalHouseConsumption));
                    });

                    // for buildings => get current consumptions
                    districtBuildings.forEach(building -> {
                        BuildingBalance buildingBalance = new BuildingBalance();
                        List<CurrentConsumption> consumptions = cityEnergyService.getCurrentConsumptionsById(
                                building.getId());
                        List<CurrentProduction> productions = cityEnergyService.getCurrentProductionsById(
                                building.getId());
                        double totalBuildingConsumption =
                                consumptions.stream().mapToDouble(CurrentConsumption::getAmount).sum();
                        double totalBuildingProduction =
                                productions.stream().mapToDouble(CurrentProduction::getAmount).sum();
                        buildingBalance.setBuilding(building);
                        buildingBalance.setAmountConsumed(totalBuildingConsumption);
                        buildingBalance.setAmountProduced(totalBuildingProduction);
                        districtBalance.addBuildingBalance(buildingBalance);
                        totalDistrictConsumption.updateAndGet(v -> (v + totalBuildingConsumption));
                        totalDistrictProduction.updateAndGet(v -> (v + totalBuildingProduction));
                    });

                    districtBalance.setEcoDistrict(district);
                    districtBalance.setAmountConsumed(totalDistrictConsumption.get());
                    districtBalance.setAmountProduced(totalDistrictProduction.get());
                    ecoDistrictBalances.add(districtBalance);
                    totalCityConsumption.updateAndGet(v -> (v + totalDistrictConsumption.get()));
                    totalCityProduction.updateAndGet(v -> (v + totalDistrictProduction.get()));
                });
                energyBalance =  totalCityProduction.get() - totalCityConsumption.get();
                balanceResponse.setDistrictsBalances(ecoDistrictBalances);
                balanceResponse.setCityBalance(energyBalance);

                Thread.sleep(SmartGridVariables.SMART_GRID_BALANCE_REFRESH);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // getters & setters
    public double getEnergyBalance() {
        return energyBalance;
    }

    public void setEnergyBalance(double energyBalance) {
        this.energyBalance = energyBalance;
    }

    public CityBalanceResponse getBalanceResponse() {
        return balanceResponse;
    }
}
