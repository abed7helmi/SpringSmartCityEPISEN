package com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DwpDeviceConsumption;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DwpDeviceConsumptionRepository extends CrudRepository<DwpDeviceConsumption, Integer> {
    @Query(value = "select * from dwp_device_consumption where device_id= :equipment_id", nativeQuery=true)
    DwpDeviceConsumption findByDeviceId(@Param("equipment_id") int equipment_id);
    @Query(value = "select consumption from dwp_device_consumption where device_id= :equipment_id", nativeQuery=true)
    Float getConsoByDeviceId(@Param("equipment_id") int equipment_id);

    @Query(value = "select c.* from dwp_device_consumption c inner join dwp_device d on device_id=id where id_room= :room_id", nativeQuery=true)
    List<DwpDeviceConsumption> getConsoDevicesbyRoom(@Param("room_id") int room_id);

    @Query(value = "select d.id, d.device_name, c.date_conso, c.consumption from dwp_device_consumption c inner join dwp_device d on device_id=id where d.type!='store' and d.type!='lumiere' and id_room=:room_id", nativeQuery=true)
    List<Object> gestInfosDevicebyRoom(@Param("room_id") int room_id);

}
