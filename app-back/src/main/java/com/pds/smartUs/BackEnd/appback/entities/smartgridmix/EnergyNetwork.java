package com.pds.smartUs.BackEnd.appback.entities.smartgridmix;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "energy_network")
public class EnergyNetwork {

    @Id
    @Column(name = "energy_network_id")
    private int id;

    public EnergyNetwork() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EnergyNetwork{" +
                "id=" + id +
                '}';
    }
}