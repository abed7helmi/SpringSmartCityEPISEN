package com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class ElectricLuminosityRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_electric_lumi;

    boolean light_state;
    int desired_luminosity;
    String date_choice;
    int id_room;

    public ElectricLuminosityRoom(boolean light_state, String date_choice, int id_room) {
        this.light_state = light_state;
        this.date_choice = date_choice;
        this.id_room = id_room;
    }

    public ElectricLuminosityRoom(boolean light_state, int desired_luminosity, String date_choice, int id_room) {
        this.light_state = light_state;
        this.desired_luminosity = desired_luminosity;
        this.date_choice = date_choice;
        this.id_room = id_room;
    }

    public ElectricLuminosityRoom() {

    }
}
