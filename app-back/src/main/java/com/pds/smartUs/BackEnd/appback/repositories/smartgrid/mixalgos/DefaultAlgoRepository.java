package com.pds.smartUs.BackEnd.appback.repositories.smartgrid.mixalgos;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.mixalgos.DefaultAlgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface DefaultAlgoRepository extends JpaRepository<DefaultAlgo, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update default_algo set wind_position = ?1, solar_position = ?2, hydraulic_position = ?3, geothermal_position = ?4 where id=1", nativeQuery = true)
    void setPosition(int windPosition, int solarPosition, int hydraulicPosition, int geothermalPosition);


}
