package com.pds.smartUs.BackEnd.appback.entities.dwpmeetingroom;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Detector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "room_id")
    Integer room_id;
    String name;
}
