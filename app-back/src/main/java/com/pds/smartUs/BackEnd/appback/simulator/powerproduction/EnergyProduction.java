package com.pds.smartUs.BackEnd.appback.simulator.powerproduction;

import java.util.Date;

public class EnergyProduction {

    private float amount;
    private Date startDate;
    private Date endDate;

    public EnergyProduction() {
        this.amount = 0;
        this.startDate = new Date();
        this.endDate = null;
    }

    public void save(Date endDate) {
        setEndDate(endDate);
        // save to db
        amount = 0;
    }

    public float getAmount() {
        return amount;
    }

    public void addToAmount(float amount) {
        this.amount += amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getInterval() {
        return "["+startDate+","+endDate+"]";
    }

}