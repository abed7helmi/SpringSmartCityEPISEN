package com.pds.smartUs.BackEnd.appback.repositories.habitation;

import com.pds.smartUs.BackEnd.appback.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Equipment,Long>  {

    public List<Equipment> findAllByHouseroomIdRoom(long idroom);


    @Query(value="SELECT * from equipment E INNER JOIN house_room2 H on E.houseroom_id_room=H.id_room where H.habitation_id_habitation = :idInhabitant ;", nativeQuery = true)
    public List<Equipment> getHouseEquipment(@Param("idInhabitant") Long idInhabitant);

    @Query(value="SELECT DISTINCT equipment_type from equipment;", nativeQuery = true)
    public List<String> getHouseEquipmentType();

    @Query(value="SELECT DISTINCT equipment_type from equipment;", nativeQuery = true)
    public List<String> getTypesById(@Param("idInhabitant") Long idInhabitant);

    @Query(value="SELECT * from equipment E INNER JOIN house_room2 H on E.houseroom_id_room=H.id_room where E.equipment_type = :equipment_type and H.habitation_id_habitation = :idInhabitant ;", nativeQuery = true)
    public List<Equipment> getHouseEquipmentByType(@Param("equipment_type") String equipmentType, @Param("idInhabitant") Long idInhabitant);

}
