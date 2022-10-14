package com.pds.smartUs.BackEnd.appback.entities.smartgridmix.mixalgos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "percentage_algo")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class PercentageAlgo {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "wind_percentage")
    private int windPercentage;

    @Column(name = "solar_percentage")
    private int solarPercentage;

    @Column(name = "hydraulic_percentage")
    private int hydraulicPercentage;

    @Column(name = "geothermal_percentage")
    private int geothermalPercentage;

    public PercentageAlgo( int windPercentage, int solarPercentage, int hydraulicPercentage, int geothermalPercentage) {
        this.id = id;
        this.windPercentage = windPercentage;
        this.solarPercentage = solarPercentage;
        this.hydraulicPercentage = hydraulicPercentage;
        this.geothermalPercentage = geothermalPercentage;
    }

    public PercentageAlgo(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWindPercentage() {
        return windPercentage;
    }

    public void setWindPercentage(int windPercentage) {
        this.windPercentage = windPercentage;
    }

    public int getSolarPercentage() {
        return solarPercentage;
    }

    public void setSolarPercentage(int solarPercentage) {
        this.solarPercentage = solarPercentage;
    }

    public int getHydraulicPercentage() {
        return hydraulicPercentage;
    }

    public void setHydraulicPercentage(int hydraulicPercentage) {
        this.hydraulicPercentage = hydraulicPercentage;
    }

    public int getGeothermalPercentage() {
        return geothermalPercentage;
    }

    public void setGeothermalPercentage(int geothermalPercentage) {
        this.geothermalPercentage = geothermalPercentage;
    }
}
