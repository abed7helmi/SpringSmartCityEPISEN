package com.pds.smartUs.BackEnd.appback.simulator.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class FrequencyTimeRequest {

    @JsonProperty(value = "time-unit", required = true)
    private String timeUnit;

    @JsonProperty(value = "time-value", required = true)
    private long timeFrequency;

    public FrequencyTimeRequest() {
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public long getTimeFrequency() {
        return timeFrequency;
    }

    public void setTimeFrequency(long timeFrequency) {
        this.timeFrequency = timeFrequency;
    }
}
