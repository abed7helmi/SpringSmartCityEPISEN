package com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class SensorLuminosity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_lumi;

    int room_luminosity;
    int outdoor_luminosity;
    String date_change;
    int id_room;
}
