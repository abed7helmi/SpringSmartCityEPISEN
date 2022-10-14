package com.pds.smartUs.BackEnd.appback.services.smartgrid.mixalgos;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.mixalgos.EnergyMixAlgos;
import com.pds.smartUs.BackEnd.appback.repositories.smartgrid.mixalgos.EnergyMixAlgosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnergyMixAlgosService {

    @Autowired
    private EnergyMixAlgosRepository energyMixAlgosRepository;

    public EnergyMixAlgos getEnergyMixAlgos(){
        return energyMixAlgosRepository.getById(1);
    }

    public void activateEconomicAlgo(){
        energyMixAlgosRepository.activateEconomicAlgo();
    }

    public void activateDefaultAlgo(){
        energyMixAlgosRepository.activateDefaultAlgo();
    }

    public void activatePercentageAlgo(){
        energyMixAlgosRepository.activatePercentageAlgo();
    }

    public void activateEnvironmentalAlgo(){
        energyMixAlgosRepository.activateEnvironmentalAlgo();
    }


}
