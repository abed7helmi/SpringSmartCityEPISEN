
package com.pds.smartUs.BackEnd.appback.controllers.habitation;



import com.pds.smartUs.BackEnd.appback.entities.Equipment;
import com.pds.smartUs.BackEnd.appback.entities.HouseRoom;
import com.pds.smartUs.BackEnd.appback.services.habitation.EquipmentService;
import com.pds.smartUs.BackEnd.appback.services.habitation.HouseRoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class HouseRoomRestController {

    private final EquipmentService equipmentService;
    private final HouseRoomService houseRoomService;

    @GetMapping("/listEquipments/{roomid}")
    public List<Equipment> listEquipments(@PathVariable long roomid){
        return equipmentService.getAllByHouseroomIdRoom(roomid);
    }


    @GetMapping("/listRooms/{habitationid}")
    public List<HouseRoom> listRooms(@PathVariable long habitationid){return houseRoomService.getAllByHabitationIdHabitation(habitationid);}


    @GetMapping("/consomptionsByHabitation/{habitationId}")
    public ResponseEntity<?> getConsumptionsByHabitationId(@PathVariable Long habitationId) {

        return ResponseEntity.ok(houseRoomService.getRoomEquipmentsConsumptionByHabitationId(habitationId));
    }

}
