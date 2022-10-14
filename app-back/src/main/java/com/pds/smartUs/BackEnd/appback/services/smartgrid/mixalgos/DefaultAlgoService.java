package com.pds.smartUs.BackEnd.appback.services.smartgrid.mixalgos;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.mixalgos.DefaultAlgo;
import com.pds.smartUs.BackEnd.appback.helpers.Helper;
import com.pds.smartUs.BackEnd.appback.repositories.smartgrid.mixalgos.DefaultAlgoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DefaultAlgoService {

    @Autowired
    private DefaultAlgoRepository defaultAlgoRepository;

    public DefaultAlgo getPositions(){
        return defaultAlgoRepository.getById(1);
    }

    public LinkedHashMap<String, Integer> getEnergiesPosition(){
        LinkedHashMap<String, Integer > energiesPosition = new LinkedHashMap<>();
        DefaultAlgo defaultAlgo = defaultAlgoRepository.getById(1);
        energiesPosition.put("wind", defaultAlgo.getWindPosition());
        energiesPosition.put("solar", defaultAlgo.getSolarPosition());
        energiesPosition.put("hydraulic", defaultAlgo.getHydraulicPosition());
        energiesPosition.put("geothermal", defaultAlgo.getGeothermalPosition());

        return (LinkedHashMap<String, Integer>) Helper.sortMapInt(energiesPosition);
    }

    public void setPosition(int windPosition, int solarPosition, int hydraulicPosition, int geothermalPosition){
        defaultAlgoRepository.setPosition(windPosition, solarPosition, hydraulicPosition, geothermalPosition);
    }

}
