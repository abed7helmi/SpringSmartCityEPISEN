package com.pds.smartUs.BackEnd.appback.entities.dwpmap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DWP_Area {

    @Id
    int id_dwp_area;
    int id_architecture;
    int x;
    int y;
    char direction;
    int id_hallway;
    String label;
    String area_name;

    public DWP_Area(int id_dwp_area, int id_architecture, int x, int y, char direction, int id_hallway, String label, String area_name) {
        this.id_dwp_area = id_dwp_area;
        this.id_architecture = id_architecture;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.id_hallway = id_hallway;
        this.label = label;
        this.area_name = area_name;
    }

    public DWP_Area() {

    }

    public int getId_dwp_area() {
        return id_dwp_area;
    }

    public void setId_dwp_area(int id_dwp_area) {
        this.id_dwp_area = id_dwp_area;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public int getId_architecture() {
        return id_architecture;
    }

    public void setId_architecture(int id_architecture) {
        this.id_architecture = id_architecture;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public int getId_hallway() {
        return id_hallway;
    }

    public void setId_hallway(int id_hallway) {
        this.id_hallway = id_hallway;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
