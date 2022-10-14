package com.pds.smartUs.BackEnd.appback.simulator.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class SimulatorParameters {

    @JsonProperty("temperature")
    private float temperature;

    @JsonProperty("wind")
    private float windSpeed;

    @JsonProperty("clouds")
    private float cloudCover;

    @JsonProperty("precipitations")
    private float precipitation;

    public float getTemperature() {
        return temperature;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public float getCloudCover() {
        return cloudCover;
    }

    public float getPrecipitation() {
        return precipitation;
    }

}
