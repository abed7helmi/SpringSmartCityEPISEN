package pds.smartus.frontend.services.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.smartus.frontend.entities.dwp.usemonitor.DwpDeviceConsumption;
import pds.smartus.frontend.repositories.dwp.usemonitor.DwpDeviceConsumptionProxy;

@Service
public class DwpDeviceConsumptionService {
    @Autowired
    private DwpDeviceConsumptionProxy dwpDeviceConsumptionProxy;

    public Iterable<Object> getConsoDevicesByRoom(int roomId) {
        return dwpDeviceConsumptionProxy.getConsoDevicesByRoom(roomId);
    }

    /*public Float getConsobyDeviceID(int eqId) {
        return dwpDeviceConsumptionProxy.getConsoByDeviceId(eqId);
    }*/
}
