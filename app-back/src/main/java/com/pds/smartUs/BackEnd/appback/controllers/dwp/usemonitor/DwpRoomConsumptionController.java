package com.pds.smartUs.BackEnd.appback.controllers.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor.DwpRoomConsumptionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DwpRoomConsumptionController {

    private final DwpRoomConsumptionService dwpRoomConsumptionService;

    public DwpRoomConsumptionController(DwpRoomConsumptionService dwpRoomConsumptionService) {
        this.dwpRoomConsumptionService = dwpRoomConsumptionService;
    }

    @GetMapping(path = "/consumption/room/{idroom}")
    public Float getConsoRoom(@PathVariable("idroom") int idroom){
        return dwpRoomConsumptionService.getSumConsumption(idroom);
    }

    @GetMapping(path = "/consoRoom")
    public List<Object> getInfosConsoRooms() {
       return dwpRoomConsumptionService.getInfosConsoRooms();
    }

}
