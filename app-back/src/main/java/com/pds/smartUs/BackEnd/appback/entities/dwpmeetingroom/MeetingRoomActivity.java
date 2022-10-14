package com.pds.smartUs.BackEnd.appback.entities.dwpmeetingroom;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity @Data
public class MeetingRoomActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String date;
    String hour;

    String movement;

    Integer detector_id;
}
