package com.pds.smartUs.BackEnd.appback.entities.smartgridmix.mixalgos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "default_algo")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class DefaultAlgo {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "wind_position")
    private int windPosition;

    @Column(name = "solar_position")
    private int solarPosition;

    @Column(name = "hydraulic_position")
    private int hydraulicPosition;

    @Column(name = "geothermal_position")
    private int geothermalPosition;

    public DefaultAlgo(int id, int windPosition, int solarPosition, int hydraulicPosition, int geothermal_position) {
        this.id = id;
        this.windPosition = windPosition;
        this.solarPosition = solarPosition;
        this.hydraulicPosition = hydraulicPosition;
        this.geothermalPosition = geothermal_position;
    }

    public DefaultAlgo(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWindPosition() {
        return windPosition;
    }

    public void setWindPosition(int windPosition) {
        this.windPosition = windPosition;
    }

    public int getSolarPosition() {
        return solarPosition;
    }

    public void setSolarPosition(int solarPosition) {
        this.solarPosition = solarPosition;
    }

    public int getHydraulicPosition() {
        return hydraulicPosition;
    }

    public void setHydraulicPosition(int hydraulicPosition) {
        this.hydraulicPosition = hydraulicPosition;
    }

    public int getGeothermalPosition() {
        return geothermalPosition;
    }

    public void setGeothermal_position(int geothermal_position) {
        this.geothermalPosition = geothermal_position;
    }

    @Override
    public String toString() {
        return "DefaultAlgo{" +
                "id=" + id +
                ", windPosition=" + windPosition +
                ", solarPosition=" + solarPosition +
                ", hydraulicPosition=" + hydraulicPosition +
                ", geothermal_position=" + geothermalPosition +
                '}';
    }

}
