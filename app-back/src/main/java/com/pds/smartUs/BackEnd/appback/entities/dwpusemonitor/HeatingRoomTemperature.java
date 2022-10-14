package com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class HeatingRoomTemperature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_heating_temp;

    Boolean heating_state;
    float desired_temp;
    String date_choice;
    int id_room;


    public HeatingRoomTemperature() {
    }

    public HeatingRoomTemperature(boolean heating_state, String date_choice, int id_room) {
        this.heating_state = heating_state;
        this.date_choice = date_choice;
        this.id_room = id_room;
    }

    public HeatingRoomTemperature(boolean heating_state, float desired_temp, String date_choice, int id_room) {
        this.heating_state = heating_state;
        this.desired_temp = desired_temp;
        this.date_choice = date_choice;
        this.id_room = id_room;
    }
}
