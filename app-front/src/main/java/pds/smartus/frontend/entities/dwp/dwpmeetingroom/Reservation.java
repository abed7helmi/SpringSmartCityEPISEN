package pds.smartus.frontend.entities.dwp.dwpmeetingroom;

import lombok.Data;

@Data
public class Reservation {

    int id;

    int id_room;
    int id_emp;

    String type_room;

    String start_date;
    String start_hour;

    String end_date;
    String end_hour;


    public Reservation() {
    }
}
