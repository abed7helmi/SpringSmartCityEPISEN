package com.pds.smartUs.BackEnd.appback.entities.dwpmeetingroom;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity @Data
public class MeetingRoomScale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer min;
    Integer max;
    Integer result;

}
