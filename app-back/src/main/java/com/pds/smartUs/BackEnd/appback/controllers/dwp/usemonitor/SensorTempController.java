package com.pds.smartUs.BackEnd.appback.controllers.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.SensorTempRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/roomtemp")
public class SensorTempController {
    private final SensorTempRepository sensorTempRepository;

    public SensorTempController(SensorTempRepository sensorTempRepository) {
        this.sensorTempRepository = sensorTempRepository;
    }
    @GetMapping("/{id_room}")
    public String getRoomTemp(@PathVariable("id_room") int id_room) {
        return sensorTempRepository.lastTempRoom(id_room);
    }

    @GetMapping("/date/{id_room}")
    public String getDateTemp(@PathVariable("id_room") int id_room) {
        return sensorTempRepository.lastDateTemp(id_room);
    }
}
