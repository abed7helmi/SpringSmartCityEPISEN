package com.pds.smartUs.BackEnd.appback.services.smartgrid.mixalgos;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.mixalgos.EconomicAlgo;
import com.pds.smartUs.BackEnd.appback.repositories.smartgrid.mixalgos.EconomicAlgoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EconomicAlgoService {

    @Autowired
    private EconomicAlgoRepository economicAlgoRepository;

    public EconomicAlgo getPrices(){
        return economicAlgoRepository.getPrices();
    }
}
