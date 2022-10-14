package com.pds.smartUs.BackEnd.appback.entities.habitation;

import javax.persistence.*;

@Entity
@Table(name ="light_parameters")
public class LightParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_light_parameters")
    int idLightParameters;

    @Column(name = "is_active")
    boolean isActive;

    @Column(name = "luminostiy")
    int luminosity;

    @Column(name = "id_house_equipment")
    int idHouseEquipment;

    public LightParameters() {
    }

    public LightParameters(boolean isActive, int luminosity, int idHouseEquipment) {
        this.isActive = isActive;
        this.luminosity = luminosity;
        this.idHouseEquipment = idHouseEquipment;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(int luminosity) {
        this.luminosity = luminosity;
    }

    public int getIdHouseEquipment() {
        return idHouseEquipment;
    }

    public void setIdHouseEquipment(int idHouseEquipment) {
        this.idHouseEquipment = idHouseEquipment;
    }
}
