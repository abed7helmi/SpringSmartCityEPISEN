package com.pds.smartUs.BackEnd.appback.simulator.workers;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy.*;
import com.pds.smartUs.BackEnd.appback.gloabalconfig.FeatureFlagsEnum;
import com.pds.smartUs.BackEnd.appback.services.globalconfigs.FeatureFlagService;
import com.pds.smartUs.BackEnd.appback.services.smartgrid.CityEnergyService;
import com.pds.smartUs.BackEnd.appback.simulator.config.SmartGridVariables;
import com.pds.smartUs.BackEnd.appback.simulator.entities.SimulatorResponse;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class ProdConsGenerator implements Runnable{


    private final CityEnergyService cityEnergyService;

    private final SimulatorRequester simulatorRequester;
    private SimulatorResponse simulationParams;
    private FeatureFlagService featureFlagService;

    public ProdConsGenerator(SimulatorRequester simulatorRequester, CityEnergyService energyService,
                                FeatureFlagService featureFlagService) {
        this.simulatorRequester = simulatorRequester;
        this.cityEnergyService = energyService;
        this.featureFlagService = featureFlagService;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.simulationParams = simulatorRequester.getSimulatorResponse();

                if(featureFlagService.getFlagStatusByName(FeatureFlagsEnum.PROD_CONS_FLAG)) {
                    this.generateHousesConsumptions();
                    this.generateBuildingsConsumptions();
                    this.generateBuildingsProductions();
                }

                Thread.sleep(SmartGridVariables.PROD_CONS_FREQUENCY);
            }
            catch (InterruptedException e) {e.printStackTrace();}
        }
    }

    public void generateBuildingsProductions() {
        List<CityBuilding> buildings = cityEnergyService.getAllCityBuildings();
        buildings.forEach(building -> {
            float amount = (float) determineBuildingProduction(building.getNbApartment());
            genericProductionSave(amount, "BEPOS",building.getId());
        });
    }

    public void generateHousesConsumptions() {
        int hour = simulationParams.getSimulatorTime().getHours();
        List<CityHouse> houses = cityEnergyService.getAllCityHouses();
        houses.forEach(house -> {
            double amount = determineHouseConsumption(hour);
            genericConsumptionSave(amount, "HOUSE", house.getId());
        });
    }

    public void generateBuildingsConsumptions() {
        int hour = simulationParams.getSimulatorTime().getHours();
        List<CityBuilding> buildings = cityEnergyService.getAllCityBuildings();
        buildings.forEach(building -> {
            double amount = determineBuildingConsumption(hour, building.getNbApartment());
            genericConsumptionSave(amount, "BEPOS",building.getId());
        });
    }

    // Helpers
    public double determineHouseConsumption(int hour) {
        // 8 day's intervals
        // max house consumption per day is 40 kwH => 0.028 per minute
        // min house consumption per day is 18 kwH => 0.0125 per minute
        double max = 0.028;
        double min = 0.0125;

        if(0 <= hour && hour < 6) {
            switch (simulationParams.getSeason()) {
                case "HIVER" -> {
                    min = 0.018;
                    max = 0.026;
                }
                case "ETE" -> max = 0.018;
                case "AUTOMNE" -> {
                    min = 0.015;
                    max = 0.025;
                }
                case "PRINTEMPS" -> {
                    min = 0.013;
                    max = 0.021;
                }
            }
        }

        else if(6 <= hour && hour < 12) {
            switch (simulationParams.getDaytype()) {
                case "WEEKDAY" : {
                    switch (simulationParams.getSeason()) {
                        case "HIVER" -> {
                            min = 0.018;
                            max = 0.023;
                        }

                        case "ETE" -> max = 0.014;

                        case "AUTOMNE" -> {
                            min = 0.018;
                            max = 0.021;
                        }

                        case "PRINTEMPS" -> {
                            min = 0.016;
                            max = 0.020;
                        }
                    }
                }

                case "WEEKEND" : {
                    switch (simulationParams.getSeason()) {
                        case "HIVER" -> {
                            min = 0.018;
                            max = 0.026;
                        }
                        case "ETE" -> max = 0.014;
                        case "AUTOMNE" -> {
                            min = 0.018;
                            max = 0.022;
                        }
                        case "PRINTEMPS" -> {
                            min = 0.016;
                            max = 0.021;
                        }
                    }
                }
            }
        }
        else if(12 <= hour && hour < 18) {
            switch (simulationParams.getDaytype()) {
                case "WEEKDAY" : {
                    switch (simulationParams.getSeason()) {
                        case "HIVER" -> max = 0.019;
                        case "ETE" -> max = 0.016;
                        case "AUTOMNE" -> max = 0.019;
                        case "PRINTEMPS" -> max = 0.018;
                    }
                }

                case "WEEKEND" : {
                    switch (simulationParams.getSeason()) {
                        case "HIVER", "AUTOMNE" -> {
                            min = 0.015;
                            max = 0.019;
                        }
                        case "ETE" -> max = 0.018;
                        case "PRINTEMPS" -> max = 0.019;
                    }
                }
            }
        }
        else if(18 <= hour && hour <= 23) {
            switch (simulationParams.getSeason()) {
                case "HIVER" -> min = 0.020;
                case "ETE" -> {
                    max = 0.021;
                    min = 0.013;
                }
                case "AUTOMNE" -> min = 0.019;
                case "PRINTEMPS" -> min = 0.018;
            }
        }
        return min + (max - min) * new Random().nextDouble();
    }


    public double determineBuildingConsumption(int hour, int nbApartments) {
        // max building consumption per day is nb_apartments * 30 kwH => 0.020 per minute
        // min building consumption per day is nb_apartments * 11 kwH => 0.007 per minute

        double max = 0.026;
        double min = 0.007;

        if(0 <= hour && hour < 6) {
            switch (simulationParams.getSeason()) {
                case "HIVER" -> {
                    min = 0.018;
                    max = 0.026;
                }
                case "ETE" -> max = 0.018;
                case "AUTOMNE" -> {
                    min = 0.015;
                    max = 0.025;
                }
                case "PRINTEMPS" -> {
                    min = 0.013;
                    max = 0.021;
                }
            }
        }

        else if(6 <= hour && hour < 12) {
            switch (simulationParams.getDaytype()) {
                case "WEEKDAY" : {
                    switch (simulationParams.getSeason()) {
                        case "HIVER" -> {
                            min = 0.018;
                            max = 0.023;
                        }

                        case "ETE" -> max = 0.014;

                        case "AUTOMNE" -> {
                            min = 0.018;
                            max = 0.021;
                        }

                        case "PRINTEMPS" -> {
                            min = 0.016;
                            max = 0.020;
                        }
                    }
                }

                case "WEEKEND" : {
                    switch (simulationParams.getSeason()) {
                        case "HIVER" -> {
                            min = 0.018;
                            max = 0.026;
                        }
                        case "ETE" -> max = 0.014;
                        case "AUTOMNE" -> {
                            min = 0.018;
                            max = 0.022;
                        }
                        case "PRINTEMPS" -> {
                            min = 0.016;
                            max = 0.021;
                        }
                    }
                }
            }
        }
        else if(12 <= hour && hour < 18) {
            switch (simulationParams.getDaytype()) {
                case "WEEKDAY" : {
                    switch (simulationParams.getSeason()) {
                        case "HIVER" -> max = 0.019;
                        case "ETE" -> max = 0.016;
                        case "AUTOMNE" -> max = 0.019;
                        case "PRINTEMPS" -> max = 0.018;
                    }
                }

                case "WEEKEND" : {
                    switch (simulationParams.getSeason()) {
                        case "HIVER", "AUTOMNE" -> {
                            min = 0.015;
                            max = 0.019;
                        }
                        case "ETE" -> max = 0.018;
                        case "PRINTEMPS" -> max = 0.019;
                    }
                }
            }
        }
        else if(18 <= hour && hour <= 23) {
            switch (simulationParams.getSeason()) {
                case "HIVER" -> min = 0.020;
                case "ETE" -> {
                    max = 0.021;
                    min = 0.013;
                }
                case "AUTOMNE" -> min = 0.019;
                case "PRINTEMPS" -> min = 0.018;
            }
        }
        double amount = min + ((max - min) * new Random().nextDouble());
        return amount * nbApartments;
    }

    public double determineBuildingProduction(int nbApartments) {
        // max building production per day is nb_apartments * 21 kwH => 0.015 per minute
        // min building production per day is 0 => 0 per minute
        return nbApartments * (new Random().nextDouble() * (0.015 - 0.001) + 0.001);
    }

    public void genericConsumptionSave(double amount, String type, int consumerId) {
        CurrentConsumption consumption = new CurrentConsumption();
        ConsumptionHistory consumptionHistory = new ConsumptionHistory();

        String time = new Date().toString();

        consumption.setConsumerId(consumerId);
        consumption.setConsumptionTime(time);
        consumption.setAmount(amount);
        consumption.setConsumerType(type);

        if(featureFlagService.getFlagStatusByName(FeatureFlagsEnum.SAVE_CONSPORD_HISTORY_FLAG)) {
            consumptionHistory.setConsumerId(consumerId);
            consumptionHistory.setConsumptionTime(time);
            consumptionHistory.setAmount(amount);
            consumptionHistory.setConsumerType(type);
        }

        cityEnergyService.saveConsumption(consumption);
        cityEnergyService.saveConsumptionHistory(consumptionHistory);
    }

    public void genericProductionSave(float amount, String type, int producerId) {
        CurrentProduction production = new CurrentProduction();
        ProductionHistory productionHistory = new ProductionHistory();

        String time = new Date().toString();

        production.setProducerId(producerId);
        production.setProductionTime(time);
        production.setAmount(amount);
        production.setProducerType(type);

        productionHistory.setProducerId(producerId);
        productionHistory.setProductionTime(time);
        productionHistory.setAmount(amount);
        productionHistory.setProducerType(type);

        cityEnergyService.saveProduction(production);
        cityEnergyService.saveProductionHistory(productionHistory);
    }

    // Getters & Setters
    public void setSimulationParams(SimulatorResponse simulationParams) {
        this.simulationParams = simulationParams;
    }
}
