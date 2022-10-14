package com.pds.smartUs.BackEnd.appback.services.smartgrid.mixalgos;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.mixalgos.PercentageAlgo;
import com.pds.smartUs.BackEnd.appback.repositories.smartgrid.mixalgos.PercentageAlgoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PercentageAlgoService {

    @Autowired
    private PercentageAlgoRepository percentageAlgoRepository;

    public PercentageAlgo getPercentages(){
        return percentageAlgoRepository.getById(1);
    }

    public HashMap<String, Integer> getEnergiesPercentage(){
        HashMap<String, Integer> energiesPercentage = new HashMap<>();
        PercentageAlgo percentageAlgo = percentageAlgoRepository.getById(1);

        energiesPercentage.put("wind", percentageAlgo.getWindPercentage());
        energiesPercentage.put("solar", percentageAlgo.getSolarPercentage());
        energiesPercentage.put("hydraulic", percentageAlgo.getHydraulicPercentage());
        energiesPercentage.put("geothermal", percentageAlgo.getGeothermalPercentage());

        return energiesPercentage;
    }

    public void setPercentage(int windPercentage, int solarPercentage, int hydraulicPercentage, int geothermalPercentage){
        percentageAlgoRepository.setPercentage(windPercentage, solarPercentage, hydraulicPercentage, geothermalPercentage);
    }

}
