package com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class SensorTemperature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_temp;

    String room_temperature;
    String outdoor_temperature;
    String date_change;
    int id_room;

    public SensorTemperature() {
    }
}
