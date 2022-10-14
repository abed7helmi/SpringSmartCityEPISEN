package com.pds.smartUs.BackEnd.appback.controllers.smartgrid.cityenergy;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.ProductionHistorical;
import com.pds.smartUs.BackEnd.appback.services.smartgrid.ProductionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductionHistoryController {

    @Autowired
    private ProductionHistoryService productionHistoryService;

    @GetMapping("/wind-history")
    public List<?> getWindHistory(){return productionHistoryService.getWindHistory();}

    @GetMapping("/solar-history")
    public List<?> getSolarHistory(){return productionHistoryService.getSolarHistory();}

    @GetMapping("/hydraulic-history")
    public List<?> getHydraulicHistory(){return productionHistoryService.getHydraulicHistory();}

    @GetMapping("/thermal-history")
    public List<?> getThermalHistory(){return productionHistoryService.getThermalHistory();}
}
