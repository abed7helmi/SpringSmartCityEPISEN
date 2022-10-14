package com.pds.smartUs.BackEnd.appback.repositories.habitation;

import com.pds.smartUs.BackEnd.appback.entities.EquipmentConsomption;
import com.pds.smartUs.BackEnd.appback.entities.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public interface EquipmentConsomptionRepository extends JpaRepository<EquipmentConsomption,Long> {


    public List<EquipmentConsomption> findEquipmentConsomptionByEquipmentIdOrderByTimeDesc(Long equipement, Pageable pageable);
    public List<EquipmentConsomption> findAllByEquipmentHouseroomIdRoom(Long roomId);

    @Query(value = "select sum(EC.consomption) from equipment_consomption EC INNER JOIN equipment e on e.id_house_equipment = EC.equipment_id_house_equipment INNER JOIN house_room2 h on h.id_room = e.houseroom_id_room where h.habitation_id_habitation=?1"+" and EC.time > ?2"+" and EC.time < ?3 ; ",nativeQuery = true)
//    @Query(value = "select sum(EC.consomption) from equipment_consomption EC INNER JOIN equipment e on e.id_house_equipment = EC.equipment_id_house_equipment INNER JOIN house_room2 h on h.id_room = e.houseroom_id_room where h.habitation_id_habitation=?1 and EC.time between ?2 and ?3;",nativeQuery = true)
    public Double findConsumptionByDate(Long idhabitation, LocalDate yes2, LocalDate minuittotime2);
//    public Double findConsumptionByDate(Long idhabitation, Timestamp yes2, Timestamp minuittotime2);


}
