package com.pds.smartUs.BackEnd.appback.repositories.smartgrid.cityenergy;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy.CurrentConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrentConsumptionRepository extends JpaRepository<CurrentConsumption, Integer> {

    List<CurrentConsumption> getCurrentConsumptionByConsumerType(String consumerType);

    List<CurrentConsumption> getCurrentConsumptionByConsumerId(int consumerId);


}
