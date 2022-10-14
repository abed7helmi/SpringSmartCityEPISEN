package com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dwp_device")
public class DwpDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name="device_name")
    String name;
    @Column(name="type")
    String type;
    String emplacement;
    int id_room;


    public DwpDevice() {

    }

}

