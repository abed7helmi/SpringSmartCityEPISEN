package com.pds.smartUs.BackEnd.appback.entities.globalconfigs;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "feature_flags")
public class FeatureFlagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_flag")
    private Integer id;

    @Column(name = "flag_name")
    private String flagName;

    @Column(name = "flag")
    private Boolean flagValue;

    public FeatureFlagEntity() {
    }

    public FeatureFlagEntity(Integer id, String flagName, boolean flagValue) {
        this.id = id;
        this.flagName = flagName;
        this.flagValue = flagValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlagName() {
        return flagName;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }

    public boolean isFlagValue() {
        return flagValue;
    }

    public void setFlagValue(boolean flagValue) {
        this.flagValue = flagValue;
    }

}

