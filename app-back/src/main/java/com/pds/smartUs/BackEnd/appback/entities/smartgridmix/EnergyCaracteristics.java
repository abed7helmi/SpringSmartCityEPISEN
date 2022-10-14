package com.pds.smartUs.BackEnd.appback.entities.smartgridmix;

import com.pds.smartUs.BackEnd.appback.simulator.enums.EnergyType;

import javax.persistence.*;

@Entity
@Table(name = "energy_caracteristics")
public class EnergyCaracteristics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "energy_caracs_id")
    private Integer id;

    @Column(name = "production_cost")
    private Float productionCost;

    @Column(name = "intermittent_risk")
    private Float intermittentRisk;

    @Column(name = "startup_time")
    private Float startupTime;

    @Column(name = "energy_type")
    private String energytype;

    public EnergyCaracteristics(){
    }

    public EnergyCaracteristics(Float productionCost, Float intermittentRisk, Float startupTime, String energytype) {
        this.productionCost = productionCost;
        this.intermittentRisk = intermittentRisk;
        this.startupTime = startupTime;
        this.energytype = energytype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(Float productionCost) {
        this.productionCost = productionCost;
    }

    public Float getIntermittentRisk() {
        return intermittentRisk;
    }

    public void setIntermittentRisk(Float intermittentRisk) {
        this.intermittentRisk = intermittentRisk;
    }

    public Float getStartupTime() {
        return startupTime;
    }

    public void setStartupTime(Float startupTime) {
        this.startupTime = startupTime;
    }

    public EnergyType getEnergytype() {
        return EnergyType.valueOf(energytype);
    }

    public void setEnergytype(String energytype) {
        this.energytype = energytype;
    }

    @Override
    public String toString() {
        return "EnergyCaracteristics{" +
                "id=" + id +
                ", productionCost=" + productionCost +
                ", intermittentRisk=" + intermittentRisk +
                ", startupTime=" + startupTime +
                ", energytype=" + energytype +
                '}';
    }
}
