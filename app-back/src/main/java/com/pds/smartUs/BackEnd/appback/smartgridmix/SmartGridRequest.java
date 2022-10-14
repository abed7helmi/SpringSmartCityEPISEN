package com.pds.smartUs.BackEnd.appback.smartgridmix;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pds.smartUs.BackEnd.appback.simulator.powersites.GeothermalPowerSite;
import com.pds.smartUs.BackEnd.appback.simulator.powersites.HydraulicPowerSite;
import com.pds.smartUs.BackEnd.appback.simulator.powersites.SolarPowerSite;
import com.pds.smartUs.BackEnd.appback.simulator.powersites.WindPowerSite;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class SmartGridRequest {

    @JsonProperty("amount")
    private float amount;

    @JsonProperty("solar-sites")
    private SolarPowerSite solarPowerSite;

    @JsonProperty("wind-sites")
    private WindPowerSite windPowerSite;

    @JsonProperty("hydraulic-sites")
    private HydraulicPowerSite hydraulicPowerSite;

    @JsonProperty("geothermal-sites")
    private GeothermalPowerSite geothermalPowerSite;

    @JsonProperty("request-time")
    private String requestTime;


    public SmartGridRequest(float amount,  WindPowerSite windPowerSite, SolarPowerSite solarPowerSite,
                            HydraulicPowerSite hydraulicPowerSite, GeothermalPowerSite geothermalPowerSite,
                            String requestTime) {
        this.amount = amount;
        this.solarPowerSite = solarPowerSite;
        this.windPowerSite = windPowerSite;
        this.hydraulicPowerSite = hydraulicPowerSite;
        this.geothermalPowerSite = geothermalPowerSite;
        this.requestTime = requestTime;
    }

    public SmartGridRequest(){}

    public float getAmount() {
        return amount;
    }

    public SolarPowerSite getSolarPowerSite() {
        return solarPowerSite;
    }

    public WindPowerSite getWindPowerSite() {
        return windPowerSite;
    }

    public HydraulicPowerSite getHydraulicPowerSite() {
        return hydraulicPowerSite;
    }

    public GeothermalPowerSite getGeothermalPowerSite() {
        return geothermalPowerSite;
    }

    public String getRequestTime() {
        return requestTime;
    }

    @Override
    public String toString() {
        return "SmartgridRequest{" +
                "amout=" + amount +
                ", solarPowerSite=" + solarPowerSite +
                ", windPowerSite=" + windPowerSite +
                ", hydraulicPowerSite=" + hydraulicPowerSite +
                ", geothermalPowerSite=" + geothermalPowerSite +
                ", requestTime=" + requestTime +
                '}';
    }
}
