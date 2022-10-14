package com.pds.smartUs.BackEnd.appback.services.habitation;

import com.pds.smartUs.BackEnd.appback.entities.EquipmentConsomption;
import com.pds.smartUs.BackEnd.appback.entities.HouseRoom;
import com.pds.smartUs.BackEnd.appback.entities.dwpmap.Room;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.HouseRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HouseRoomService {

    private final HouseRoomRepository houseRoomRepository;

    private final EquipmentService equipmentService;
    public List getAllByHabitationIdHabitation (Long habitationId){

        return houseRoomRepository.findAllByHabitationIdHabitation(habitationId);

    }

    public Map<String, Double>  getRoomEquipmentsConsumptionByHabitationId(Long habitationId){
        List<HouseRoom> rooms = houseRoomRepository.findAllByHabitationIdHabitation(habitationId);
        Map <String, Double> roomAndSums = new HashMap<>();
        rooms.stream().forEach(room -> {
            Map<String, List<EquipmentConsomption>> equipmentConsumption = equipmentService.getEquipmentsConsumptionByRoomId(room.getIdRoom(), 1);
            double ttlCunsumption = 0;
            for(Map.Entry<String, List<EquipmentConsomption>> entry : equipmentConsumption.entrySet()){
                ttlCunsumption += entry.getValue().get(0).getConsomption();
            }
            roomAndSums.put(room.getRoomName(), ttlCunsumption);
        });

        return roomAndSums;
    }

}
