package com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dwp_room_consumption")
public class DwpRoomConsumption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_cons;

    Float consumption;
    String date_conso;
    int id_room;

    public DwpRoomConsumption(Float consumption, String date_conso, int id_room) {
        this.consumption = consumption;
        this.date_conso = date_conso;
        this.id_room = id_room;
    }

    public DwpRoomConsumption() {

    }
}
