package com.pds.smartUs.BackEnd.appback.entities.dwpmap;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Building {

    @Id
    int id_building;

    String building_name;

    public Building(){

    }

    public Building(int id_building, String building_name) {
        this.id_building = id_building;
        this.building_name = building_name;
    }

    public int getId_building() {
        return id_building;
    }

    public void setId_building(int id_building) {
        this.id_building = id_building;
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }
}
