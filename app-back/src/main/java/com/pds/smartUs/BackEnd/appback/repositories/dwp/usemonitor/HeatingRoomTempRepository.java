package com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.HeatingRoomTemperature;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HeatingRoomTempRepository extends CrudRepository<HeatingRoomTemperature, Integer> {
    @Query(value = "select heating_state from heating_room_temperature where id_room= :id_room ORDER BY id_heating_temp DESC LIMIT 1", nativeQuery=true)
    boolean heating_state(@Param("id_room") int id_room);

    @Query(value = "select desired_temp from heating_room_temperature where id_room= :id_room ORDER BY id_heating_temp DESC LIMIT 1", nativeQuery=true)
    Integer lastDesiredTemp(@Param("id_room") int id_room);

    @Query(value = "select e.id from dwp_device e inner join room_test r on r.id_room=e.id_room where e.type='chauffage' and r.id_room =:id_room", nativeQuery=true)
    Integer getIdChauffage(@Param("id_room") int id_room);

}
