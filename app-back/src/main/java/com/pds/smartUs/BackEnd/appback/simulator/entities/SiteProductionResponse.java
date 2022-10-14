package com.pds.smartUs.BackEnd.appback.simulator.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.ProductionSiteModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

@Data
@NoArgsConstructor
@JsonComponent
public class SiteProductionResponse {
    @JsonProperty("production-site")
    private ProductionSiteModel productionSite;

    @JsonProperty("production-amount")
    private double amount;

    @JsonProperty("city-balance")
    private double cityBalance;

    @JsonProperty("production-time")
    private String time;

}
