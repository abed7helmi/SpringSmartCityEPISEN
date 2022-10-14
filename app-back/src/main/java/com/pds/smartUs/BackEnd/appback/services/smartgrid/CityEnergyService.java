package com.pds.smartUs.BackEnd.appback.services.smartgrid;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy.*;
import com.pds.smartUs.BackEnd.appback.repositories.smartgrid.cityenergy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityEnergyService {
    @Autowired
    private CityBuildingRepository buildingRepository;

    @Autowired
    private CityHouseRepository houseRepository;

    @Autowired
    private CurrentProductionRepository currentProductionRepository;

    @Autowired
    private CurrentConsumptionRepository currentConsumptionRepository;

    @Autowired
    private ProductionHistoryRepository productionHistoryRepository;

    @Autowired
    private ConsumptionHistoryRepository consumptionHistoryRepository;

    @Autowired
    private EcoDistrictRepository ecoDistrictRepository;

    public List<CityHouse> getAllCityHouses() {
        return houseRepository.findAll();
    }

    public List<CityBuilding> getAllCityBuildings() {
        return buildingRepository.findAll();
    }

    public List<CityBuilding> getCityBuildingsByDistrictId(int id) {
        return buildingRepository.getCityBuildingByDistrict_Id(id);
    }

    public List<CityHouse> getCityHousesByDistrictId(int id) {
        return houseRepository.getCityHouseByDistrict_Id(id);
    }

    public List<EcoDistrict> getAllEcoDistricts() {
        return ecoDistrictRepository.findAll();
    }

    public void saveConsumption(CurrentConsumption consumption) {
        currentConsumptionRepository.save(consumption);
    }

    public void saveConsumptionHistory(ConsumptionHistory consumptionHistory) {
        consumptionHistoryRepository.save(consumptionHistory);
    }

    public void saveProduction(CurrentProduction production) {
        currentProductionRepository.save(production);
    }

    public void saveProductionHistory(ProductionHistory productionHistory) {
        productionHistoryRepository.save(productionHistory);
    }

    public List<CurrentProduction> getBuildingsCurrentProductions() {
        return currentProductionRepository.getCurrentProductionByProducerType("BEPOS");
    }

    public List<CurrentConsumption> getBuildingsCurrentConsumptions() {
        return currentConsumptionRepository.getCurrentConsumptionByConsumerType("BEPOS");
    }

    public List<CurrentConsumption> getHousesCurrentConsumptions() {
        return currentConsumptionRepository.getCurrentConsumptionByConsumerType("HOUSE");
    }

    public List<CurrentConsumption> getCurrentConsumptionsById(int id) {
        return currentConsumptionRepository.getCurrentConsumptionByConsumerId(id);
    }

    public List<CurrentProduction> getCurrentProductionsById(int id) {
        return currentProductionRepository.getCurrentProductionByProducerId(id);
    }

    public void deleteAllCurrentConsumptions() {
        currentConsumptionRepository.deleteAll();
    }

    public void deleteAllCurrentProductions() {
        currentProductionRepository.deleteAll();
    }

}
