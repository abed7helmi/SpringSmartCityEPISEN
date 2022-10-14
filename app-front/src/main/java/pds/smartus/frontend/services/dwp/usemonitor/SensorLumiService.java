package pds.smartus.frontend.services.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.smartus.frontend.repositories.dwp.usemonitor.SensorLumiProxy;

@Service
public class SensorLumiService {
    @Autowired
    private SensorLumiProxy sensorLumiProxy;

    public int getLumiRoom(int idroom) {
        return sensorLumiProxy.getRoomLumi(idroom);
    }

    public int getOutdoorRoom(int idroom) {
        return sensorLumiProxy.getOutdoorLumi(idroom);
    }
}
