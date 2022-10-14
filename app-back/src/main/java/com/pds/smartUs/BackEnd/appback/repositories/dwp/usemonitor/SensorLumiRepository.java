package com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.SensorLuminosity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorLumiRepository extends CrudRepository<SensorLuminosity, Integer> {

    @Query(value = "select room_luminosity from sensor_luminosity where id_room =:id_room ORDER BY id_lumi DESC LIMIT 1", nativeQuery = true)
    int lastLumiRoom(@Param("id_room") int id_room);

    @Query(value = "select outdoor_luminosity from sensor_luminosity where id_room =:id_room ORDER BY id_lumi DESC LIMIT 1", nativeQuery = true)
    int outdoorLumi(@Param("id_room") int id_room);

}
