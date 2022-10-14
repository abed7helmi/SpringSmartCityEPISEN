package pds.smartus.frontend.entities.dwp.dwpmeetingroom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pds.smartus.frontend.entities.dwp.dwpmap.Room;

@Data @AllArgsConstructor @NoArgsConstructor
public class RoomStatus {
    private Room room;
    private Boolean status;
}
