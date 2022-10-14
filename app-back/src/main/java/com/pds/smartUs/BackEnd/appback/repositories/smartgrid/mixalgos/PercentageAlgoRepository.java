package com.pds.smartUs.BackEnd.appback.repositories.smartgrid.mixalgos;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.mixalgos.PercentageAlgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PercentageAlgoRepository extends JpaRepository<PercentageAlgo, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update percentage_algo set wind_percentage = ?1, solar_percentage = ?2, hydraulic_percentage = ?3, geothermal_percentage = ?4 where id=1", nativeQuery = true)
    void setPercentage(int windPercentage, int solarPercentage, int hydraulicPercentage, int geothermalPercentage);
}
