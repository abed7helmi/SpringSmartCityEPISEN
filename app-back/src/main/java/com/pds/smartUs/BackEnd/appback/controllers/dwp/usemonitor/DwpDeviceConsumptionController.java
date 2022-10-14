package com.pds.smartUs.BackEnd.appback.controllers.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor.DwpDeviceConsumptionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DwpDeviceConsumptionController {
    private final DwpDeviceConsumptionService dwpDeviceConsumptionService;

    public DwpDeviceConsumptionController(DwpDeviceConsumptionService dwpDeviceConsumptionService) {
        this.dwpDeviceConsumptionService = dwpDeviceConsumptionService;
    }

    @PostMapping(path = "/addcafe/{deviceId}")
    public void addConsoCafe(@PathVariable("deviceId") int deviceId) {
        dwpDeviceConsumptionService.addConsoForCafe(deviceId);
    }

    @GetMapping(path = "/consoDevices/room/{roomId}")
    public List<Object> getInfosConsoDevicesbyRoom(@PathVariable("roomId") int roomId) {
        return dwpDeviceConsumptionService.getinfosDevicesbyRoom(roomId);
    }

    @GetMapping(path = "/conso/device/{eqId}")
    public Float getConsoByDeviceId(@PathVariable("eqId") int eqId) {
        return dwpDeviceConsumptionService.getConsoByDeviceId(eqId);
    }
}
