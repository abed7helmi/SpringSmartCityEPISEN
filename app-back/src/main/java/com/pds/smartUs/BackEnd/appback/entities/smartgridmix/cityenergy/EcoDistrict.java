package com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eco_district")
public class EcoDistrict {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "district_name")
    private String districtName;

    public EcoDistrict() {
    }

    public EcoDistrict(int id, String districtName) {
        this.id = id;
        this.districtName = districtName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
