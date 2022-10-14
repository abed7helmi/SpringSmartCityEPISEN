package com.pds.smartUs.BackEnd.appback.entities.smartgridmix;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "energy_production_statistics")
public class EnergyProductionStatistics {

    @Id
    @Column(name = "energy_production_id")
    private int id;

    @Column(name = "latest_date")
    private String latestDate;

    @Column(name = "ennr_prod")
    private float ennrProd;

    @Column(name = "solar_prod")
    private float solarProd;

    @Column(name = "wind_prod")
    private float windProd;

    @Column(name = "hydraulic_prod")
    private float hydraulicProd;

    @Column(name = "geothermal_prod")
    private float geothermalProd;

    public EnergyProductionStatistics(){
    }

    public EnergyProductionStatistics(String latestDate, float ennrProd, float solarProd, float windProd, float hydraulicProd, float geothermalProd) {
        this.latestDate = latestDate;
        this.ennrProd = ennrProd;
        this.solarProd = solarProd;
        this.windProd = windProd;
        this.hydraulicProd = hydraulicProd;
        this.geothermalProd = geothermalProd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatestDate() {
        return latestDate;
    }

    public void setLatestDate(String latestDate) {
        this.latestDate = latestDate;
    }

    public float getEnnrProd() {
        return ennrProd;
    }

    public void setEnnrProd(float ennrProd) {
        this.ennrProd = ennrProd;
    }

    public float getSolarProd() {
        return solarProd;
    }

    public void setSolarProd(float solarProd) {
        this.solarProd = solarProd;
    }

    public float getWindProd() {
        return windProd;
    }

    public void setWindProd(float windProd) {
        this.windProd = windProd;
    }

    public float getHydraulicProd() {
        return hydraulicProd;
    }

    public void setHydraulicProd(float hydraulicProd) {
        this.hydraulicProd = hydraulicProd;
    }

    public float getGeothermalProd() {
        return geothermalProd;
    }

    public void setGeothermalProd(float geothermalProd) {
        this.geothermalProd = geothermalProd;
    }

    @Override
    public String toString() {
        return "EnergyProductionStatistics{" +
                "id=" + id +
                ", latestDate='" + latestDate + '\'' +
                ", ennrProd=" + ennrProd +
                ", solarProd=" + solarProd +
                ", windProd=" + windProd +
                ", hydraulicProd=" + hydraulicProd +
                ", geothermalProd=" + geothermalProd +
                '}';
    }
}
