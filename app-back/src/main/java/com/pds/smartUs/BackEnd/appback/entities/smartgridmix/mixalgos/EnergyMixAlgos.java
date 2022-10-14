package com.pds.smartUs.BackEnd.appback.entities.smartgridmix.mixalgos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "energy_mix_algos")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class EnergyMixAlgos {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "economic_algo_state")
    private boolean economicAlgoState;

    @Column(name = "default_algo_state")
    private boolean defaultAlgoState;

    @Column(name = "percentage_algo_state")
    private boolean percentageAlgoState;

    @Column(name = "environmental_algo_state")
    private boolean environmentAlgoState;

    public EnergyMixAlgos( boolean economicAlgoState, boolean defaultAlgoState, boolean percentageAlgoState, boolean environmentAlgoState) {
        this.id = id;
        this.economicAlgoState = economicAlgoState;
        this.defaultAlgoState = defaultAlgoState;
        this.percentageAlgoState = percentageAlgoState;
        this.environmentAlgoState = environmentAlgoState;
    }

    public EnergyMixAlgos(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEconomicAlgoState() {
        return economicAlgoState;
    }

    public void setEconomicAlgoState(boolean economicAlgoState) {
        this.economicAlgoState = economicAlgoState;
    }

    public boolean isDefaultAlgoState() {
        return defaultAlgoState;
    }

    public void setDefaultAlgoState(boolean defaultAlgoState) {
        this.defaultAlgoState = defaultAlgoState;
    }

    public boolean isPercentageAlgoState() {
        return percentageAlgoState;
    }

    public void setPercentageAlgoState(boolean percentageAlgoState) {
        this.percentageAlgoState = percentageAlgoState;
    }

    public boolean isEnvironmentAlgoState() {
        return environmentAlgoState;
    }

    public void setEnvironmentAlgoState(boolean environmentAlgoState) {
        this.environmentAlgoState = environmentAlgoState;
    }
}
