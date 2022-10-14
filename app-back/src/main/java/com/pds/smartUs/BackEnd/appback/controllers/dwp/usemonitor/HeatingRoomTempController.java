package com.pds.smartUs.BackEnd.appback.controllers.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.HeatingRoomTemperature;
import com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor.HeatingRoomTempService;
import org.springframework.web.bind.annotation.*;

@RestController
public class HeatingRoomTempController {
    private final HeatingRoomTempService heatingRoomTempService;

    public HeatingRoomTempController(HeatingRoomTempService heatingRoomTempService) {
        this.heatingRoomTempService = heatingRoomTempService;
    }

    @GetMapping
    public Iterable<HeatingRoomTemperature> getAllHeatingTemp() {
        return heatingRoomTempService.getAllHeatingInfos();
    }

    @GetMapping(path = "/heating/state/{idroom}")
    public boolean getHeatingState(@PathVariable("idroom") int idroom) {
        return heatingRoomTempService.getHeatingState(idroom);
    }

    @GetMapping(path = "/heating/temp/{idroom}")
    public Integer getDesiredTemp(@PathVariable("idroom") int idroom) {
        return heatingRoomTempService.getLastDesiredTemp(idroom);
    }

    @PostMapping(path = "/heating/add")
    public void useHeating(@RequestParam("state") boolean state, @RequestParam("temp") float temp, @RequestParam("date") String date, @RequestParam("idroom") int idroom) {
        HeatingRoomTemperature heatingRoomTemperature = new HeatingRoomTemperature(state, temp, date, idroom);
        heatingRoomTempService.useHeating(heatingRoomTemperature);
    }

    @PostMapping(path = "/heating/off")
    public void turnOffHeating(@RequestParam("date") String date, @RequestParam("idroom") int idroom) {
        HeatingRoomTemperature heatingRoomTemperature = new HeatingRoomTemperature(false, date, idroom);
        heatingRoomTempService.useHeating(heatingRoomTemperature);
    }

    /*@PostMapping("/add/{state}/{temp}/{date}/{idroom}")
    public void useHeating(@PathVariable("state") boolean state, @PathVariable("temp") float temp, @PathVariable("date") String date, @PathVariable("idroom") int idroom) {
        heatingRoomTempService.useHeating(state, temp, date, idroom);
    }*/
}
