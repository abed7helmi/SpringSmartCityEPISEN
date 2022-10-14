package com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy;

import javax.persistence.*;

@Entity
@Table(name = "current_energy_consumption")
public class CurrentConsumption {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "amount_consumed")
    private double amount;

    @Column(name = "consumption_time")
    private String consumptionTime;

    @Column(name = "consumer_type")
    private String consumerType;

    @Column(name = "consumer_id")
    private int consumerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CurrentConsumption() {
    }

    public CurrentConsumption(int id, double amount, String consumptionTime, String consumerType, int consumerId) {
        this.id = id;
        this.amount = amount;
        this.consumptionTime = consumptionTime;
        this.consumerType = consumerType;
        this.consumerId = consumerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getConsumptionTime() {
        return consumptionTime;
    }

    public void setConsumptionTime(String consumptionTime) {
        this.consumptionTime = consumptionTime;
    }

    public String getConsumerType() {
        return consumerType;
    }

    public void setConsumerType(String consumerType) {
        this.consumerType = consumerType;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }
}