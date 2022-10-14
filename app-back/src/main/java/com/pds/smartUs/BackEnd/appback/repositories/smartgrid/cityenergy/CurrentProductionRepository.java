package com.pds.smartUs.BackEnd.appback.repositories.smartgrid.cityenergy;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy.CurrentConsumption;
import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy.CurrentProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrentProductionRepository extends JpaRepository<CurrentProduction, Integer> {

    List<CurrentProduction> getCurrentProductionByProducerType(String producerType);

    List<CurrentProduction> getCurrentProductionByProducerId(int producerId);
}
