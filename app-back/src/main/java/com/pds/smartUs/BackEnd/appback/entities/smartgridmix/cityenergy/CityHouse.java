package com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy;

import javax.persistence.*;

@Entity
@Table(name = "city_house")
public class CityHouse {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "house_address")
    private String address;

    @Column(name = "unit_number")
    private int nbUnits;

    @Column(name = "tenants_number")
    private int nbTenants;

    @OneToOne
    @JoinColumn(name = "eco_district_id")
    private EcoDistrict district;

    public CityHouse() {
    }

    public CityHouse(int id, String address, int nbUnits, int nbTenants, EcoDistrict district) {
        this.id = id;
        this.address = address;
        this.nbUnits = nbUnits;
        this.nbTenants = nbTenants;
        this.district = district;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String buildingName) {
        this.address = buildingName;
    }

    public int getNbUnits() {
        return nbUnits;
    }

    public void setNbUnits(int nbFloors) {
        this.nbUnits = nbFloors;
    }

    public int getNbTenants() {
        return nbTenants;
    }

    public void setNbTenants(int nbTenants) {
        this.nbTenants = nbTenants;
    }

    public EcoDistrict getDistrict() {
        return district;
    }

    public void setDistrict(EcoDistrict district) {
        this.district = district;
    }
}
