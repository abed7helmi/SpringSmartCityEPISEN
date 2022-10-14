package com.pds.smartUs.BackEnd.appback.simulator.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

@Data
@NoArgsConstructor
@JsonComponent
public class SimulatorTime {
    @JsonProperty("hours")
    private int hours;

    @JsonProperty("minutes")
    private int minutes;

    @JsonProperty("seconds")
    private int seconds;

    @Override
    public String toString() {
        return hours + "h" + minutes + "m"+ seconds + "s";
    }
}
