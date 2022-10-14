package com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DeviceActivity;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.DeviceActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceActivityService {
    private final DeviceActivityRepository deviceActivityRepository;

    @Autowired
    public DeviceActivityService(DeviceActivityRepository deviceActivityRepository) {
        this.deviceActivityRepository = deviceActivityRepository;
    }

    public List<DeviceActivity> getDeviceActivities() {
        return (List<DeviceActivity>) deviceActivityRepository.findAll();
    }

    public void addNewDeviceActivity(DeviceActivity deviceActivity) {
        deviceActivityRepository.save(deviceActivity);
    }

    public boolean updateActivity(int equipment_id) {
        return !deviceActivityRepository.is_active(equipment_id);
    }

    public DeviceActivity lastActivity(int equipment_id) {
        return deviceActivityRepository.lastActivity(equipment_id);
    }
}
