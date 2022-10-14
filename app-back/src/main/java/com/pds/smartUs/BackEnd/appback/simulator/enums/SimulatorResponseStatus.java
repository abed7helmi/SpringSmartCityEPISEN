package com.pds.smartUs.BackEnd.appback.simulator.enums;

public enum SimulatorResponseStatus {
    SUCCESS("success"), ERROR("error");

    private String code;

    SimulatorResponseStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
