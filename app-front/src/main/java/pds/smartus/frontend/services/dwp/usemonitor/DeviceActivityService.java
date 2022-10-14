package pds.smartus.frontend.services.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.smartus.frontend.entities.dwp.usemonitor.DeviceActivity;
import pds.smartus.frontend.repositories.dwp.usemonitor.DeviceActivityProxy;

@Service
public class DeviceActivityService {

    @Autowired
    private DeviceActivityProxy deviceActivityProxy;

    public DeviceActivity saveDeviceActivity(DeviceActivity deviceActivity, int equipment) {
            return deviceActivityProxy.addNewDeviceActivity(deviceActivity,equipment);
    }

    public DeviceActivity getLastActivity(int equipment) {
        return deviceActivityProxy.getLastActivity(equipment);
    }
}
