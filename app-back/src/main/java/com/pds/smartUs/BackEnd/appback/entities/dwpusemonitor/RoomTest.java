package com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RoomTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_room;

    String room_name;
    String room_type;
    int width;
    int height;

    public RoomTest(){}

    public RoomTest(String room_name, String room_type, int width, int height) {
        this.room_name = room_name;
        this.room_type = room_type;
        this.width = width;
        this.height = height;
    }

    public int getId_room() {
        return id_room;
    }

    public void setId_room(int id_room) {
        this.id_room = id_room;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
