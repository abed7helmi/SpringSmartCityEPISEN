package com.pds.smartUs.BackEnd.appback.controllers.habitation;

import com.pds.smartUs.BackEnd.appback.repositories.habitation.DeviceRepository;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.EquipmentConsomptionRepository;
import com.pds.smartUs.BackEnd.appback.services.habitation.EquipmentService;
import com.pds.smartUs.BackEnd.appback.services.habitation.ProductionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DeviceConsommationRestController {

    private final EquipmentService equipmentService;

    @GetMapping("/consomption/{equipmentId}")
    public ResponseEntity<?> listProd(@PathVariable Long equipmentId) {
        return ResponseEntity.ok(equipmentService.getEquipmentConsomptionByEquipmentId(equipmentId));
    }

    @GetMapping("/consomptionsByRoom/{roomId}")
    public ResponseEntity<?> getConsumptionsByRoomId(@PathVariable Long roomId) {
        return ResponseEntity.ok(equipmentService.getEquipmentsConsumptionByRoomId(roomId, 100));
    }


}
