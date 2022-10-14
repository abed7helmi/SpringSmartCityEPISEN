package pds.smartus.frontend.services.habitation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.smartus.frontend.entities.habitation.HouseRoom;
import pds.smartus.frontend.repositories.habitation.HouseRoomProxy;

@Service
public class HouseRoomService {

    @Autowired
    private HouseRoomProxy houseRoomProxy;

    public Iterable<HouseRoom> getRooms(Long habitationid){
        return houseRoomProxy.getRooms(habitationid);
    }
}
