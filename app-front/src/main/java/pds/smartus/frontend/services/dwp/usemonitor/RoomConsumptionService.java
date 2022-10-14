package pds.smartus.frontend.services.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.smartus.frontend.repositories.dwp.usemonitor.RoomConsumptionProxy;

@Service
public class RoomConsumptionService {
    @Autowired
    private RoomConsumptionProxy roomConsumptionProxy;

    public Float getRoomConsumption(int idroom){
        return roomConsumptionProxy.getRoomConsumption(idroom);
    }

}
