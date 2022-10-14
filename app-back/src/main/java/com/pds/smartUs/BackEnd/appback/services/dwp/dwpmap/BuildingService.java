package com.pds.smartUs.BackEnd.appback.services.dwp.dwpmap;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.Building;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmap.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;

    @Autowired
    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    public List<Building> getBuildings() {
        return (List<Building>) buildingRepository.findAll();
    }

    public Building getBuildingById(int id_building) {
        return buildingRepository.findById(id_building).get();
    }
}
