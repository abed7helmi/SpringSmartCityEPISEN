package com.pds.smartUs.BackEnd.appback.simulator.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pds.smartUs.BackEnd.appback.simulator.powersites.ProductionSite;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class SmartGridResponse {

    @JsonProperty("solarSite")
    private ProductionSite solarSite;

    @JsonProperty("windSite")
    private ProductionSite windSite;

    @JsonProperty("hydraulicSite")
    private ProductionSite hydraulicSite;

    @JsonProperty("thermalSite")
    private ProductionSite thermalSite;

    public ProductionSite getSolarSite() {
        return solarSite;
    }

    public void setSolarSite(ProductionSite solarSite) {
        this.solarSite = solarSite;
    }

    public ProductionSite getWindSite() {
        return windSite;
    }

    public void setWindSite(ProductionSite windSite) {
        this.windSite = windSite;
    }

    public ProductionSite getHydraulicSite() {
        return hydraulicSite;
    }

    public void setHydraulicSite(ProductionSite hydraulicSite) {
        this.hydraulicSite = hydraulicSite;
    }

    public ProductionSite getThermalSite() {
        return thermalSite;
    }

    public void setThermalSite(ProductionSite thermalSite) {
        this.thermalSite = thermalSite;
    }
}
