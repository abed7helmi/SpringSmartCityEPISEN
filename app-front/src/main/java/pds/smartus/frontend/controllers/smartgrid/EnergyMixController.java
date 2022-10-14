package pds.smartus.frontend.controllers.smartgrid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import pds.smartus.frontend.services.smartgrid.EnergyMixService;

@Controller
public class EnergyMixController {

    @Autowired
    private EnergyMixService energyMixService;

    @GetMapping("/productioninfos")
    public String getProdInfos(Model md){
        return "smartgrid/energy-mix";
    }

    @GetMapping("/mix-algos")
    public String getAlgosInfos(Model md ) { return "smartgrid/mix-algos"; }

    @GetMapping("/production-history")
    public String getProductionHistory(Model md ) { return "smartgrid/production-history"; }

    @PostMapping("/activate-economic-algo")
    public String activateEconomicAlgo(){
        System.out.println("Activate");
        energyMixService.activateEconomicAlgo();
        return "smartgrid/energy-mix";
    }

    @PostMapping("/activate-percentage-algo")
    public String activatePercentageAlgo(){
        System.out.println("Activate");
        energyMixService.activateEconomicAlgo();
        return "smartgrid/energy-mix";
    }

    @PostMapping("/activate-default-algo")
    public String activateDefaultAlgo(){
        System.out.println("Activate");
        energyMixService.activateEconomicAlgo();
        return "smartgrid/energy-mix";
    }

}
