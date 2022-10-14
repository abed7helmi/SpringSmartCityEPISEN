package com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.SensorTemperature;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorTempRepository extends CrudRepository<SensorTemperature, Integer> {

    @Query(value = "select room_temperature from sensor_temperature where id_room =:id_room ORDER BY id_temp DESC LIMIT 1", nativeQuery = true)
    String lastTempRoom(@Param("id_room") int id_room);

    @Query(value = "select outdoor_temperature from sensor_temperature where id_room =:id_room ORDER BY id_temp DESC LIMIT 1", nativeQuery = true)
    String lastOutdoorTemp(@Param("id_room") int id_room);

    @Query(value = "select date_change from sensor_temperature where id_room =:id_room ORDER BY id_temp DESC LIMIT 1", nativeQuery = true)
    String lastDateTemp(@Param("id_room") int id_room);
}
