package com.pds.smartUs.BackEnd.appback.controllers.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DeviceActivity;
import com.pds.smartUs.BackEnd.appback.helpers.Helper;
import com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor.DeviceActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/deviceactivity")
public class DeviceActivityController {

    private final DeviceActivityService deviceActivityService;

    @Autowired
    public DeviceActivityController(DeviceActivityService deviceActivityService) {
        this.deviceActivityService = deviceActivityService;
    }

    @GetMapping
    public Iterable<DeviceActivity> getDeviceActivities() {
        return deviceActivityService.getDeviceActivities();
    }

    @PostMapping(path = "/{equipment_id}")
    public void registerNewActivity(@PathVariable("equipment_id") int equipment_id) {
        DeviceActivity deviceActivity = new DeviceActivity(deviceActivityService.updateActivity(equipment_id), Helper.getDateNow(), equipment_id);
        deviceActivityService.addNewDeviceActivity(deviceActivity);
    }

    @GetMapping(path="/last/{equipment_id}")
    public DeviceActivity getLastActivity(@PathVariable("equipment_id") int equipment_id) {
        return deviceActivityService.lastActivity(equipment_id);
    }
}
