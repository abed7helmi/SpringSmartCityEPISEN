package pds.smartus.frontend.services.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.smartus.frontend.repositories.dwp.usemonitor.SensorTempProxy;

@Service
public class SensorTempService {
    @Autowired
    private SensorTempProxy sensorTempProxy;

    public String getRoomTemp(int roomId) {
        return sensorTempProxy.getRoomTemp(roomId);
    }

    public String getDateTemp(int roomId) {
        return sensorTempProxy.getDateTemp(roomId);
    }
}
