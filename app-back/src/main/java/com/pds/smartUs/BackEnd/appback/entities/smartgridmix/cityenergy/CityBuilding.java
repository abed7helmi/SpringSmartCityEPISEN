package com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy;

import javax.persistence.*;

@Entity
@Table(name = "city_building")
public class CityBuilding {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "building_name")
    private String buildingName;

    @Column(name = "number_floors")
    private int nbFloors;

    @Column(name = "tenants_number")
    private int nbTenants;

    @Column(name = "apartment_number")
    private int nbApartment;

    @OneToOne
    @JoinColumn(name = "eco_district_id")
    private EcoDistrict district;

    public CityBuilding() {
    }

    public CityBuilding(int id, String buildingName, int nbFloors, int nbTenants, int nbApartment, EcoDistrict district) {
        this.id = id;
        this.buildingName = buildingName;
        this.nbFloors = nbFloors;
        this.nbTenants = nbTenants;
        this.nbApartment = nbApartment;
        this.district = district;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getNbFloors() {
        return nbFloors;
    }

    public void setNbFloors(int nbFloors) {
        this.nbFloors = nbFloors;
    }

    public int getNbTenants() {
        return nbTenants;
    }

    public void setNbTenants(int nbTenants) {
        this.nbTenants = nbTenants;
    }

    public int getNbApartment() {
        return nbApartment;
    }

    public void setNbApartment(int nbApartment) {
        this.nbApartment = nbApartment;
    }

    public EcoDistrict getDistrict() {
        return district;
    }

    public void setDistrict(EcoDistrict district) {
        this.district = district;
    }
}
