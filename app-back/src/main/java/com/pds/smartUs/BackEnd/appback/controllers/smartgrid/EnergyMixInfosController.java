package com.pds.smartUs.BackEnd.appback.controllers.smartgrid;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.MixResponses;
import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.mixalgos.DefaultAlgo;
import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.mixalgos.EnergyMixAlgos;
import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.mixalgos.PercentageAlgo;
import com.pds.smartUs.BackEnd.appback.helpers.Helper;
import com.pds.smartUs.BackEnd.appback.services.smartgrid.MixResponsesService;
import com.pds.smartUs.BackEnd.appback.services.smartgrid.mixalgos.DefaultAlgoService;
import com.pds.smartUs.BackEnd.appback.services.smartgrid.mixalgos.EnergyMixAlgosService;
import com.pds.smartUs.BackEnd.appback.services.smartgrid.mixalgos.PercentageAlgoService;
import com.pds.smartUs.BackEnd.appback.smartgridmix.MixResponse;
import com.pds.smartUs.BackEnd.appback.smartgridmix.SmartGridRequest;
import com.pds.smartUs.BackEnd.appback.smartgridmix.energymix.MixAlgorithms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/energy-mix")
public class EnergyMixInfosController {

   @Autowired
   private MixAlgorithms mixAlgorithms;

   @Autowired
   private MixResponsesService mixResponsesService;

   @Autowired
   private EnergyMixAlgosService energyMixAlgosService;

   @Autowired
   private PercentageAlgoService percentageAlgoService;

   @Autowired
   private DefaultAlgoService defaultAlgoService;

   private MixResponses mixResponses;

    @PostMapping(value = "/mix-infos",
    consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public MixResponse request(@RequestBody SmartGridRequest smartgridRequest) {
        MixResponse mixResponse;
        EnergyMixAlgos energyMixAlgos = energyMixAlgosService.getEnergyMixAlgos();
        HashMap<String, Integer> energiesPercentage = percentageAlgoService.getEnergiesPercentage();
        System.out.println();
        HashMap<String, Integer> eneriesPosition = defaultAlgoService.getEnergiesPosition();

        if (energyMixAlgos.isPercentageAlgoState()) {
            System.out.println("Percentage Algo");
            mixResponse = mixAlgorithms.percentageAlgorithm(smartgridRequest, energiesPercentage);
        } else if (energyMixAlgos.isEconomicAlgoState()) {
            System.out.println("Economic Algo");
            mixResponse = mixAlgorithms.economicAlgorithm(smartgridRequest);
        } else if (energyMixAlgos.isEnvironmentAlgoState()){
            System.out.println("Environmental Algo");
            mixResponse = mixAlgorithms.environmentalAlgorithm(smartgridRequest);
        } else {
            System.out.println("Default Algo");
            mixResponse = mixAlgorithms.simpleAlgorithm(smartgridRequest, eneriesPosition);
        }

        System.out.println("Mix " + mixResponse);
        mixResponses = new MixResponses(smartgridRequest.getAmount(),
                Helper.getDateNow(),mixResponse.getWindEnergyAmount().getAmount(),
                mixResponse.getSolarEnergyAmount().getAmount(),mixResponse.getHydraulicEnergyAmount().getAmount(),
                mixResponse.getGeothermalEnergyAmount().getAmount());
        mixResponsesService.saveMixResponse(mixResponses);
        return mixResponse;
    }

    @GetMapping("/mix-result")
    public MixResponses energyMixResult(){
        return mixResponsesService.getLastMixResponse();
    }

    @GetMapping("/latest-mix-result")
    public Iterable<MixResponses> getLastestMixResponses(){
        return mixResponsesService.getLastestMixResponses();
    }

    @GetMapping("/percentage-algo-parameters")
    public PercentageAlgo getPercentages(){
        return percentageAlgoService.getPercentages();
    }

    @GetMapping("/default-algo-parameters")
    public DefaultAlgo getPositions(){
        return defaultAlgoService.getPositions();
    }

    @GetMapping("/algos-state")
    public EnergyMixAlgos getEnergyMixAlgos(){
        return energyMixAlgosService.getEnergyMixAlgos();
    }

    //@GetMapping("/production-history")


    @PostMapping("activate-economic-algo")
    public void activateEconomicAlgo(){
        System.out.println("Activate");
        energyMixAlgosService.activateEconomicAlgo();
    }

    @PostMapping("activate-percentage-algo")
    public void activatePercentageAlgo(@RequestParam("wind")int windPercentage, @RequestParam("solar") int solarPercentage, @RequestParam("hydraulic") int hydraulicPercentage, @RequestParam("geothermal") int geothermalPercentage){
        energyMixAlgosService.activatePercentageAlgo();
        percentageAlgoService.setPercentage(windPercentage, solarPercentage, hydraulicPercentage, geothermalPercentage);
    }

    @PostMapping("activate-default-algo")
    public void activateDefaultAlgo(@RequestParam("wind")int windPosition, @RequestParam("solar") int solarPosition, @RequestParam("hydraulic") int hydraulicPosition, @RequestParam("geothermal") int geothermalPosition){
        energyMixAlgosService.activateDefaultAlgo();
        defaultAlgoService.setPosition(windPosition, solarPosition, hydraulicPosition, geothermalPosition);
    }

    @PostMapping("activate-environmental-algo")
    public void activateEnvironmentalAlgo(){
        energyMixAlgosService.activateEnvironmentalAlgo();
    }
}