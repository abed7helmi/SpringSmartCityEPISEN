package com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DeviceActivity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceActivityRepository extends CrudRepository<DeviceActivity, Integer> {

    @Query(value = "select is_active from dwp_device_activity where device_id= :equipment_id ORDER BY activity_id DESC LIMIT 1", nativeQuery=true)
    Boolean is_active(@Param("equipment_id") int equipment_id);

    @Query(value = "select * from dwp_device_activity where device_id= :equipment_id ORDER BY activity_id DESC LIMIT 1", nativeQuery=true)
    DeviceActivity lastActivity(@Param("equipment_id") int equipment_id);

}
