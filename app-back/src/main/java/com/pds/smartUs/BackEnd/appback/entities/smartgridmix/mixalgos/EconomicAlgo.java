package com.pds.smartUs.BackEnd.appback.entities.smartgridmix.mixalgos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "economic_algo")
public class EconomicAlgo {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "wind_price")
    private float windPrice;

    @Column(name = "solar_price")
    private float solarPrice;

    @Column(name = "hydraulic_price")
    private float hydraulicPrice;

    @Column(name = "geothermal_price")
    private float geothermalPrice;

    public EconomicAlgo(float windPrice, float solarPrice, float hydraulicPrice, float geothermalPrice) {
        this.id = id;
        this.windPrice = windPrice;
        this.solarPrice = solarPrice;
        this.hydraulicPrice = hydraulicPrice;
        this.geothermalPrice = geothermalPrice;
    }

    public EconomicAlgo(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getWindPrice() {
        return windPrice;
    }

    public void setWindPrice(float windPrice) {
        this.windPrice = windPrice;
    }

    public float getSolarPrice() {
        return solarPrice;
    }

    public void setSolarPrice(float solarPrice) {
        this.solarPrice = solarPrice;
    }

    public float getHydraulicPrice() {
        return hydraulicPrice;
    }

    public void setHydraulicPrice(float hydraulicPrice) {
        this.hydraulicPrice = hydraulicPrice;
    }

    public float getGeothermalPrice() {
        return geothermalPrice;
    }

    public void setGeothermalPrice(float geothermalPrice) {
        this.geothermalPrice = geothermalPrice;
    }
}
