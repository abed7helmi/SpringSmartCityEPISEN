package com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dwp_device_consumption")
public class DwpDeviceConsumption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_cons;

    Float consumption;
    String date_conso;
    int device_id;

}
