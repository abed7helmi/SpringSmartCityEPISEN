package com.pds.smartUs.BackEnd.appback.simulator.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.ProductionSiteModel;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class SiteCapacitiesResponse {
    @JsonProperty("production-site")
    private ProductionSiteModel productionSite;

    @JsonProperty("production-capacity")
    private double capacity;

    public SiteCapacitiesResponse() {
    }

    public ProductionSiteModel getProductionSite() {
        return productionSite;
    }

    public void setProductionSite(ProductionSiteModel productionSite) {
        this.productionSite = productionSite;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }
}
