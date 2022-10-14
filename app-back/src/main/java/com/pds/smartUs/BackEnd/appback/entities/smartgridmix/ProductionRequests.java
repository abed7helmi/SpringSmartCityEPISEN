package com.pds.smartUs.BackEnd.appback.entities.smartgridmix;

import com.pds.smartUs.BackEnd.appback.models.RequestState;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "production_requests")
public class ProductionRequests {

    @Id
    @Column(name = "production_requests_id")
    private int id;

    @Column(name = "request_date",
            nullable = false)
    private String requestDate;

    @Column(name = "request_amount",
            nullable = false)
    private float requestAmount;

    @Column(name = "request_state",
            nullable = false)
    private RequestState requestState;

    public ProductionRequests(){
    }

    public ProductionRequests(String requestDate, float requestAmount, RequestState requestState) {
        this.requestDate = requestDate;
        this.requestAmount = requestAmount;
        this.requestState = requestState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public float getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(float requestAmount) {
        this.requestAmount = requestAmount;
    }

    public RequestState getRequestState() {
        return requestState;
    }

    public void setRequestState(RequestState requestState) {
        this.requestState = requestState;
    }

    @Override
    public String toString() {
        return "ProductionRequests{" +
                "id=" + id +
                ", requestDate='" + requestDate + '\'' +
                ", requestAmount=" + requestAmount +
                ", requestState=" + requestState +
                '}';
    }
}