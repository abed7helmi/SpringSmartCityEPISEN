package com.pds.smartUs.BackEnd.appback.services.smartgrid;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.ProductionSiteModel;
import com.pds.smartUs.BackEnd.appback.repositories.smartgrid.ProductionSiteRepository;
import com.pds.smartUs.BackEnd.appback.simulator.enums.EnergyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionSiteService {

    @Autowired
    private ProductionSiteRepository productionSiteRepository;

    public List<ProductionSiteModel> getProductionSite() {
        return productionSiteRepository.findAll();
    }

    public List<ProductionSiteModel> getFossilProductionSites() {
        return productionSiteRepository.getProductionSiteModelsByEnergyCaracs_Id(5);
    }
}
