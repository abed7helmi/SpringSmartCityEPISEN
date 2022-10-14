package pds.smartus.frontend.services.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.smartus.frontend.entities.dwp.dwpmap.Room;
import pds.smartus.frontend.repositories.dwp.dwpmap.DWPMapProxy;
import pds.smartus.frontend.repositories.dwp.usemonitor.RoomTestProxy;

@Service
public class RoomService {

    @Autowired
    private DWPMapProxy dwpMapProxy;

    @Autowired
    private RoomTestProxy roomTestProxy;

    /*public Room getRoomById(int room_id) {
        return dwpMapProxy.getRoom(room_id);
    }*/

    public Room getRoomTest(int room_id) {
        return roomTestProxy.getRoomTest(room_id);
    }
}
