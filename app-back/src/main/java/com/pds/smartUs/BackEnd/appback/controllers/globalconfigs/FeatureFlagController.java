package com.pds.smartUs.BackEnd.appback.controllers.globalconfigs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pds.smartUs.BackEnd.appback.entities.globalconfigs.FeatureFlagEntity;
import com.pds.smartUs.BackEnd.appback.services.globalconfigs.FeatureFlagService;
import com.pds.smartUs.BackEnd.appback.simulator.entities.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feature-flags")
public class FeatureFlagController {

    @Autowired
    private FeatureFlagService featureFlagService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public List<FeatureFlagEntity> getFeatureFlags() {
        return featureFlagService.getFeatureFlags();
    }

    @PostMapping(value = "/flag", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public GenericResponse updateFeatureFlag(@RequestBody FeatureFlagRequest request) {
        featureFlagService.updateFeatureFlag(request.getFlagId(), request.isFlagValue());
        return new GenericResponse(1000, GenericResponse.Status.success,
                "feature flag has been updated successfully");
    }
}

@JsonComponent
class FeatureFlagRequest {
    @JsonProperty("flag_id")
    private int flagId;

    @JsonProperty("flag_value")
    private boolean flagValue;

    public FeatureFlagRequest() {
    }

    public int getFlagId() {
        return flagId;
    }

    public void setFlagId(int flagId) {
        this.flagId = flagId;
    }

    public boolean isFlagValue() {
        return flagValue;
    }

    public void setFlagValue(boolean flagValue) {
        this.flagValue = flagValue;
    }
}
