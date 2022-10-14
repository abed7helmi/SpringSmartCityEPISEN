package com.pds.smartUs.BackEnd.appback.repositories.smartgrid;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.ProductionSiteModel;
import com.pds.smartUs.BackEnd.appback.simulator.enums.EnergyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionSiteRepository extends JpaRepository<ProductionSiteModel, Integer> {
    List<ProductionSiteModel> getProductionSiteModelsByEnergyCaracs_Id(int energyCaracsId);
}
