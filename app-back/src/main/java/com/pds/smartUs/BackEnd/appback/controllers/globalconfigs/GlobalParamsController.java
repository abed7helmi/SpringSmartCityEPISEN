package com.pds.smartUs.BackEnd.appback.controllers.globalconfigs;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.pds.smartUs.BackEnd.appback.entities.globalconfigs.GlobalParam;
import com.pds.smartUs.BackEnd.appback.services.globalconfigs.GlobalParamsService;
import com.pds.smartUs.BackEnd.appback.simulator.entities.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/global-params")
public class GlobalParamsController {

    @Autowired
    private GlobalParamsService globalParamsService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public List<GlobalParam> getGlobalParams() {
        return globalParamsService.getGlobalParams();
    }

    @PostMapping(value = "/param", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public GenericResponse updateGlobalParam(@RequestBody GlobalParamRequest request) {
        globalParamsService.updateGlobalParam(request.getParamId(), request.getParamValue());
        return new GenericResponse(1000, GenericResponse.Status.success,
                "global param has been updated successfully");
    }
}

@JsonComponent
class GlobalParamRequest {
    @JsonProperty("param_id")
    private int paramId;

    @JsonProperty("param_value")
    private String paramValue;

    public GlobalParamRequest() {
    }

    public int getParamId() {
        return paramId;
    }

    public void setParamId(int paramId) {
        this.paramId = paramId;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }
}