package com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmeetingroom;

import com.pds.smartUs.BackEnd.appback.entities.dwpmeetingroom.Detector;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DetectorRepository extends CrudRepository<Detector,Integer> {
    @Query(value = "select * from detector where room_id= :room_id ", nativeQuery=true)
    Optional<Detector> findByRoom_id(@Param("room_id") Integer room_id);
}
