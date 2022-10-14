package pds.smartus.frontend.services.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.smartus.frontend.repositories.dwp.usemonitor.DwpRoomConsumptionProxy;

@Service
public class DwpRoomConsumptionService {
    @Autowired
    private DwpRoomConsumptionProxy dwpRoomConsumptionProxy;

    public Iterable<Object> getInfosConsoRoom() {
        return dwpRoomConsumptionProxy.getInfosConsoByRoom();
    }

}
