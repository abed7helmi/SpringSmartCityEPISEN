package com.pds.smartUs.BackEnd.appback.simulator.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@JsonComponent
public class GenericResponse {
    public enum Status {success, error};

    @JsonProperty("code")
    private int code;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("message")
    private String message;

    private final HashMap<String, Object> customFields = new HashMap<>();

    public GenericResponse() {
    }

    public GenericResponse(int code, Status status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GenericResponse withCustomField(String key, Object value) {
        this.customFields.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return '{' +
                "\"code\" : \"" + code + "\",\n" +
                "\"status\" : \"" + status + "\",\n" +
                "\"message\" : \""  + message + "\"" +
                getCustomFields()+
                '}';
    }

    private String getCustomFields() {
        if(customFields.size() <= 0) return "";

        StringBuilder sb = new StringBuilder();
        sb.append(",\n");
        AtomicInteger count = new AtomicInteger();
        customFields.forEach((key, field)-> {
            sb.append("\"").append(key).append("\" : \"").append(field.toString()).append("\"");
            if(customFields.size() - 1 > count.get()) sb.append(",");
            sb.append("\n");
            count.getAndIncrement();
        });

        return sb.toString();
    }
}
