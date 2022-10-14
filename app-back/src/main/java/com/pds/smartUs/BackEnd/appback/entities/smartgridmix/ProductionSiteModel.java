package com.pds.smartUs.BackEnd.appback.entities.smartgridmix;

import javax.persistence.*;

@Entity
@Table(name = "production_site")
public class ProductionSiteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "production_site_id")
    private Integer id;

    @Column(name = "site_name", nullable = false)
    private String siteName;

    @Column(name = "max_production_capacity")
    private Integer maxProductionCapacity;

    @OneToOne
    @JoinColumn(name = "energy_caracs_id")
    private EnergyCaracteristics energyCaracs;

    public ProductionSiteModel(){
    }

    public ProductionSiteModel(String siteName, Integer maxProductionCapacity, EnergyCaracteristics energyCaracs) {
        this.siteName = siteName;
        this.maxProductionCapacity = maxProductionCapacity;
        this.energyCaracs = energyCaracs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public float getMaxProductionCapacity() {
        return maxProductionCapacity;
    }

    public void setMaxProductionCapacity(Integer maxProductionCapacity) {
        this.maxProductionCapacity = maxProductionCapacity;
    }

    public EnergyCaracteristics getEnergyCaracs() {
        return energyCaracs;
    }

    public void setEnergyCaracs(EnergyCaracteristics energyCaracs) {
        this.energyCaracs = energyCaracs;
    }

    @Override
    public String toString() {
        return "ProductionSite{" +
                "id=" + id +
                ", siteName='" + siteName + '\'' +
                ", maxProductionCapacity=" + maxProductionCapacity +
                '}';
    }
}