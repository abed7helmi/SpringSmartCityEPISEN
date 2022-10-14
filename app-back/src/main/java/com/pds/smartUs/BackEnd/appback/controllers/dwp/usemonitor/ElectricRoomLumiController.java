package com.pds.smartUs.BackEnd.appback.controllers.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.ElectricLuminosityRoom;
import com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor.ElectricLumiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElectricRoomLumiController {

    private final ElectricLumiService electricLumiService;

    public ElectricRoomLumiController(ElectricLumiService electricLumiService) {
        this.electricLumiService = electricLumiService;
    }

    @PostMapping(path = "/lumi/add")
    public void useLight(@RequestParam("state") boolean state, @RequestParam("lumi") int lumi, @RequestParam("date") String date, @RequestParam("idroom") int idroom) {
        ElectricLuminosityRoom electricLuminosityRoom = new ElectricLuminosityRoom(state, lumi, date, idroom);
        electricLumiService.useLight(electricLuminosityRoom);
    }

    @PostMapping(path = "/lumi/off")
    public void offLight(@RequestParam("state") boolean state, @RequestParam("date") String date, @RequestParam("idroom") int idroom) {
        ElectricLuminosityRoom electricLuminosityRoom = new ElectricLuminosityRoom(state, date, idroom);
        electricLumiService.useLight(electricLuminosityRoom);
    }

}
