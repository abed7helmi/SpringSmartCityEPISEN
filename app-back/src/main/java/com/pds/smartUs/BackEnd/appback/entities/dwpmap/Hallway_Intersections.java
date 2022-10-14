package com.pds.smartUs.BackEnd.appback.entities.dwpmap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hallway_Intersections {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_hallway_intersection;
    int id_intersection;
    int id_hallway;
    String label;
    String direction;
    int x;
    int y;

    public Hallway_Intersections(){}

    public Hallway_Intersections(int id_intersection, int id_hallway, String label, String direction, int id_hallway_intersection, int x,int y) {
        this.id_intersection = id_intersection;
        this.id_hallway = id_hallway;
        this.label = label;
        this.direction = direction;
        this.id_hallway_intersection=id_hallway_intersection;
        this.x=x;
        this.y=y;
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

    public int getId_intersection() {
        return id_intersection;
    }

    public void setId_intersection(int id_intersection) {
        this.id_intersection = id_intersection;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getId_hallway_intersection() {
        return id_hallway_intersection;
    }

    public void setId_hallway_intersection(int id_hallway_intersection) {
        this.id_hallway_intersection = id_hallway_intersection;
    }
}
