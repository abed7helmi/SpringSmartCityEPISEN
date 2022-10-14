package com.pds.smartUs.BackEnd.appback.entities.habitation;

import javax.persistence.*;

@Entity
@Table(name ="equipment_user")
public class EquipmentUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_house_equipment")
    int idHouseEquipment;

    @Column(name = "id_inhabitant")
    int id_inhabitant;

    public EquipmentUser() {
    }

    public EquipmentUser(int idHouseEquipment, int id_inhabitant) {
        this.idHouseEquipment = idHouseEquipment;
        this.id_inhabitant = id_inhabitant;
    }

    public int getIdHouseEquipment() {
        return idHouseEquipment;
    }

    public void setIdHouseEquipment(int idHouseEquipment) {
        this.idHouseEquipment = idHouseEquipment;
    }

    public int getId_inhabitant() {
        return id_inhabitant;
    }

    public void setId_inhabitant(int id_inhabitant) {
        this.id_inhabitant = id_inhabitant;
    }
}
