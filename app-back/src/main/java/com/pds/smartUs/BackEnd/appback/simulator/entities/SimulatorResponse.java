package com.pds.smartUs.BackEnd.appback.simulator.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.jackson.JsonComponent;

@Data
@JsonComponent
public class SimulatorResponse {

    @JsonProperty(value = "time")
    private SimulatorTime simulatorTime;

    @JsonProperty(value="params")
    private SimulatorParameters parameters;

    @JsonProperty(value="season")
    private String season;

    @JsonProperty(value="daytype")
    private String daytype;

}
