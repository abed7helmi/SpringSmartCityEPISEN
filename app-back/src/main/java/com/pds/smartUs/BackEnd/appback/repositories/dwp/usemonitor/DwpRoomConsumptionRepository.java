package com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DwpDeviceConsumption;
import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DwpRoomConsumption;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DwpRoomConsumptionRepository extends CrudRepository<DwpRoomConsumption, Integer> {

    @Query(value = "select SUM(consumption) from dwp_device_consumption c inner join dwp_device d on d.id=c.device_id where d.id_room=:id_room", nativeQuery=true)
    Float getSumConsumption(@Param("id_room") int id_room);

    @Query(value = "select * from dwp_room_consumption where id_room= :room_id", nativeQuery=true)
    DwpRoomConsumption findByRoomId(@Param("room_id") int room_id);

    @Query(value = "select d.id_room, d.room_type, c.date_conso, c.consumption from room_test d inner join dwp_room_consumption c on d.id_room=c.id_room where c.consumption is not null", nativeQuery=true)
    List<Object> gestInfosConsoRooms();
}
