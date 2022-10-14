package com.pds.smartUs.BackEnd.appback.entities.globalconfigs;

import javax.persistence.*;

@Entity
@Table(name = "global_params")
public class GlobalParam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gparam")
    private Integer id;

    @Column(name = "param_name")
    private String paramName;

    @Column(name = "param_value")
    private String paramValue;

    public GlobalParam() {
    }

    public GlobalParam(Integer id, String paramName, String paramValue) {
        this.id = id;
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }
}
