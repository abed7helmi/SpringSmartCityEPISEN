package com.pds.smartUs.BackEnd.appback.repositories.smartgrid.mixalgos;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.MixResponses;
import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.mixalgos.EconomicAlgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EconomicAlgoRepository extends JpaRepository<EconomicAlgo, Integer> {

    @Query(value = "SELECT * FROM economic_algo WHERE id = 1", nativeQuery = true)
    EconomicAlgo getPrices();
}
