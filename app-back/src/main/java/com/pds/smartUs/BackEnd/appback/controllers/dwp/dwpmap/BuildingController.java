package com.pds.smartUs.BackEnd.appback.controllers.dwp.dwpmap;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.Building;
import com.pds.smartUs.BackEnd.appback.services.dwp.dwpmap.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BuildingController {

    private final BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("/buildings")
    public List<Building> getBuildings(){
        return buildingService.getBuildings();
    }

    @GetMapping("/building/{id_building}")
    public Building getBuildingById(@PathVariable("id_building") int id_building){
        return buildingService.getBuildingById(id_building);
    }
}
