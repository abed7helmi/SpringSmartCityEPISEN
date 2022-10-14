package com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy;


import javax.persistence.*;

@Entity
@Table(name = "energy_production_history")
public class ProductionHistory {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "amount_produced")
    private double amount;

    @Column(name = "production_time")
    private String productionTime;

    @Column(name = "producer_type")
    private String producerType;

    @Column(name = "producer_id")
    private int producerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductionHistory() {
    }

    public ProductionHistory(int id, double amount, String productionTime, String producerType, int producerId) {
        this.id = id;
        this.amount = amount;
        this.productionTime = productionTime;
        this.producerType = producerType;
        this.producerId = producerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(String productionTime) {
        this.productionTime = productionTime;
    }

    public String getProducerType() {
        return producerType;
    }

    public void setProducerType(String producerType) {
        this.producerType = producerType;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }
}
