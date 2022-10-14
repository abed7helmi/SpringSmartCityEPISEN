package com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "id_room")
    int id_room;
    @Column(name = "id_emp")
    int id_emp;

    @Column(name = "type_room")
    String type_room;

    String start_date;
    String start_hour;

    String end_date;
    String end_hour;


    public Reservation() {
    }
}
