package com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DwpDevice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DwpDeviceRepository extends CrudRepository<DwpDevice, Integer> {
    @Query(value = "select * from dwp_device where id_room= :id_room", nativeQuery=true)
    List<DwpDevice> equipmentsByRoom(@Param("id_room") int id_room);

    @Query(value = "select e.id from dwp_device e inner join room_test r on r.id_room=e.id_room where e.type='PC' and r.id_room =:id_room", nativeQuery=true)
    int getIdPC(@Param("id_room") int id_room);
}
