package com.pds.smartUs.BackEnd.appback.simulator.workers;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy.CityBuilding;
import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy.CityHouse;
import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy.CurrentConsumption;
import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy.CurrentProduction;
import com.pds.smartUs.BackEnd.appback.services.smartgrid.CityEnergyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Random;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProdConsGeneratorTest implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {}

}

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(ProdConsGeneratorTest.class)
class GeneratorTest {

    @Autowired
    private ProdConsGenerator prodConsGenerator;

    @Autowired
    private SimulatorRequester requester;

    @Autowired
    private CityEnergyService energyService;

    @Test
    void generateBuildingsProductions() {
        prodConsGenerator.setSimulationParams(requester.getSimulatorResponse());
        // Given
        List<CityBuilding> buildings = energyService.getAllCityBuildings();
        List<CurrentProduction> listProductionBefore = energyService.getBuildingsCurrentProductions();

        // When
        prodConsGenerator.generateBuildingsProductions();
        List<CurrentProduction> listProductionAfter = energyService.getBuildingsCurrentProductions();

        // Then
        Assertions.assertEquals(listProductionAfter.size(), listProductionBefore.size() + buildings.size());
    }

    @Test
    void generateHousesConsumptions() {
        prodConsGenerator.setSimulationParams(requester.getSimulatorResponse());
        // Given
        List<CityHouse> houses = energyService.getAllCityHouses();
        List<CurrentConsumption> listConsumptionBefore = energyService.getHousesCurrentConsumptions();

        // When
        prodConsGenerator.generateHousesConsumptions();
        List<CurrentConsumption> listConsumptionAfter = energyService.getHousesCurrentConsumptions();

        // Then
        Assertions.assertEquals(listConsumptionAfter.size(), listConsumptionBefore.size() + houses.size());
    }

    @Test
    void generateBuildingsConsumptions() {
        prodConsGenerator.setSimulationParams(requester.getSimulatorResponse());
        // Given
        List<CityBuilding> buildings = energyService.getAllCityBuildings();
        List<CurrentConsumption> listConsumptionBefore = energyService.getBuildingsCurrentConsumptions();

        // When
        prodConsGenerator.generateBuildingsConsumptions();
        List<CurrentConsumption> listConsumptionAfter = energyService.getBuildingsCurrentConsumptions();

        // Then
        Assertions.assertEquals(listConsumptionAfter.size(), listConsumptionBefore.size() + buildings.size());
    }

    @Test
    void determineHouseConsumption() {
        prodConsGenerator.setSimulationParams(requester.getSimulatorResponse());

        for(int i=0; i <= 100000; i++) {
            double generated = prodConsGenerator.determineHouseConsumption(i);
            assert generated >= 0.0125 && generated <= 0.028;
        }
    }

    @Test
    void determineBuildingConsumption() {
        prodConsGenerator.setSimulationParams(requester.getSimulatorResponse());

        for(int i=0; i <= 100000; i++) {
            double generated = prodConsGenerator.determineBuildingConsumption(i, i);
            assert generated >= i * 0.007 && generated <= i * 0.026;
        }
    }

    @Test
    void determineBuildingProduction() {
        for(int i=0; i <= 100000; i++) {
            double generated = prodConsGenerator.determineBuildingProduction(i);
            assert generated >= i * 0.001 && generated <= i * 0.015;
        }
    }

    @Test
    void GenericConsumptioSave_ForBuildingsAndHouses_CreatesTwoRowsInCurrentProductionTable() {
        // Given
        List<CurrentConsumption> listBuildingConsumptionBefore = energyService.getBuildingsCurrentConsumptions();

        // When
        prodConsGenerator.genericConsumptionSave(new Random().nextDouble(), "BEPOS", 1);
        List<CurrentConsumption> listBuildingConsumptionAfter = energyService.getBuildingsCurrentConsumptions();

        // Then
        Assertions.assertEquals(listBuildingConsumptionAfter.size(), listBuildingConsumptionBefore.size() + 1);

        // Given
        List<CurrentConsumption> listHouseConsumptionBefore = energyService.getHousesCurrentConsumptions();

        // When
        prodConsGenerator.genericConsumptionSave(new Random().nextDouble(), "HOUSE", 1);
        List<CurrentConsumption> listHouseConsumptionAfter = energyService.getHousesCurrentConsumptions();

        // Then
        Assertions.assertEquals(listHouseConsumptionAfter.size(), listHouseConsumptionBefore.size() + 1);

        Assertions.assertEquals(listBuildingConsumptionAfter.size() + listHouseConsumptionAfter.size()
        , listHouseConsumptionBefore.size() + listBuildingConsumptionBefore.size() + 2);

    }

    @Test
    void GenericProductionSave_ForBuildings_CreatesOneRowInCurrentProductionTable() {
        // Given
        List<CurrentProduction> listBuildingProductionBefore = energyService.getBuildingsCurrentProductions();

        // When
        prodConsGenerator.genericProductionSave(new Random().nextFloat(), "BEPOS", 1);
        List<CurrentProduction> listBuildingProductionAfter = energyService.getBuildingsCurrentProductions();

        // Then
        Assertions.assertEquals(listBuildingProductionAfter.size(), listBuildingProductionBefore.size() + 1);
    }
}