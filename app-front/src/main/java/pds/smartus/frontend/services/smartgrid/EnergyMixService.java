package pds.smartus.frontend.services.smartgrid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.smartus.frontend.entities.energymix.EnergyProductionStatistics;
import pds.smartus.frontend.repositories.smartgridmix.EnergyMixProxy;

import java.util.List;

@Service
public class EnergyMixService {

    @Autowired
    private EnergyMixProxy energyMixProxy;

    public void activateEconomicAlgo(){
        energyMixProxy.activateEconomicAlgo();
    }
}
