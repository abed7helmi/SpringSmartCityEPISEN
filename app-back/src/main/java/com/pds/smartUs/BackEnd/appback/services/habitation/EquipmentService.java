package com.pds.smartUs.BackEnd.appback.services.habitation;


import com.pds.smartUs.BackEnd.appback.entities.Equipment;
import com.pds.smartUs.BackEnd.appback.entities.EquipmentConsomption;
import com.pds.smartUs.BackEnd.appback.entities.Production;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.DeviceRepository;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.EquipmentConsomptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service@AllArgsConstructor
public class EquipmentService {
    private final EquipmentConsomptionRepository equipmentConsomptionRepository;

    private final DeviceRepository deviceRepository;


    public List getEquipmentConsomptionByEquipmentId(Long equipmentId){
        Pageable pageable = PageRequest.of(0, 10);
        return equipmentConsomptionRepository.findEquipmentConsomptionByEquipmentIdOrderByTimeDesc(equipmentId, pageable);

    }

    public Map<String, List<EquipmentConsomption>> getEquipmentsConsumptionByRoomId(Long roomId, int limit){
        Map<String, List<EquipmentConsomption>> consumptionByEquipment = new HashMap<>();

        List<EquipmentConsomption> equipmentsConsumtions = equipmentConsomptionRepository.findAllByEquipmentHouseroomIdRoom(roomId);
        Collections.reverse(equipmentsConsumtions);
        equipmentsConsumtions.stream()
                .forEach( equipmentConsumtion -> {
            List consumptions = consumptionByEquipment.get(equipmentConsumtion.getEquipment().getName());
            if (consumptions == null)
                consumptions = new ArrayList();
            consumptions.add(equipmentConsumtion);
            consumptionByEquipment.put(equipmentConsumtion.getEquipment().getName(), consumptions);
        });
        for(Map.Entry<String, List<EquipmentConsomption>> entry : consumptionByEquipment.entrySet()){
            entry.setValue(entry.getValue().stream().limit(limit).collect(Collectors.toList()));
        }
        return consumptionByEquipment;

    }

    public List<Double> getConsumptionBydate (Long idhabitation, ArrayList<LocalDate> dates){

        List<Double> result = new ArrayList<>();

        for (int i = 0; i < dates.size()-1; i++) {
            Double x=equipmentConsomptionRepository.findConsumptionByDate(idhabitation,dates.get(i+1),dates.get(i));
            result.add(x);
        }

        return result;
    }





    public List<Equipment> getHouseEquipment(Long idInhabitant) {
        return (List<Equipment>) deviceRepository.getHouseEquipment(idInhabitant);
    }

    public List<String> getHouseEquipmentType() {
        return deviceRepository.getHouseEquipmentType();
    }

    public List<Equipment> getHouseEquipmentByType(String type, Long idInhabitant) {
        return deviceRepository.getHouseEquipmentByType(type, idInhabitant);
    }

    public List<String> getTypesById(Long idInhabitant) {
        return deviceRepository.getTypesById(idInhabitant);
    }


    public List getAllByHouseroomIdRoom(Long roomid){

        return deviceRepository.findAllByHouseroomIdRoom(roomid);

    }



}
