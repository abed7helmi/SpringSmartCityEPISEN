package pds.smartus.frontend.entities.dwp.dwpmeetingroom;

import lombok.Data;


@Data
public class MeetingRoomActivity {
    Long id;
    String date;
    String hour;

    String movement;
    String duration;
    Integer detector_id;
}
