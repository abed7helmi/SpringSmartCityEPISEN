package com.pds.smartUs.BackEnd.appback.simulator.powersites;

import org.springframework.stereotype.Component;

@Component
public interface ProductionSiteObservable {
    void update(ProductionSite productionSite);
}
