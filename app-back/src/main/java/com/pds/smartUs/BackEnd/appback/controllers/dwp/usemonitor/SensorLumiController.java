package com.pds.smartUs.BackEnd.appback.controllers.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor.SensorLumiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping(path = "/roomlumi")
public class SensorLumiController {
    private final SensorLumiService sensorLumiService;

    public SensorLumiController(SensorLumiService sensorLumiService) {
        this.sensorLumiService = sensorLumiService;
    }

    @GetMapping("/{id_room}")
    public int getRoomLumi(@PathVariable("id_room") int id_room) {
        return sensorLumiService.getLastLumi(id_room);
    }

    @GetMapping("/out/{id_room}")
    public int getOutdoorLumi(@PathVariable("id_room") int id_room) {
        return sensorLumiService.getOutdoorLumi(id_room);
    }

    public int getLumiTemp(@PathVariable("id_room") int id_room) {
        return sensorLumiService.getLastLumi(id_room);
    }
}
