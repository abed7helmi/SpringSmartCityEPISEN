package com.pds.smartUs.BackEnd.appback.simulator.powersites;

import com.pds.smartUs.BackEnd.appback.simulator.enums.EnergyType;
import com.pds.smartUs.BackEnd.appback.simulator.config.SmartGridVariables;
import com.pds.smartUs.BackEnd.appback.simulator.powerproduction.EnergyProduction;
import com.pds.smartUs.BackEnd.appback.simulator.powerproduction.EnergyProductionRequest;

import java.util.ArrayList;
import java.util.Date;

public abstract class ProductionSite {
    protected EnergyProduction energyProduction;
    protected ArrayList<ProductionSiteObservable> observers = new ArrayList<>();
    protected float currentCapacity;
    protected boolean isProducing;

    abstract void produce(EnergyProductionRequest productionRequest);
    abstract public EnergyType getEnergyType();
    abstract public float getMaxCapacity();

    protected void checkAndSaveProduction(EnergyProductionRequest productionRequest) {
        Date start = energyProduction.getStartDate();
        Date now = new Date();
        // produce energy
        if(productionRequest.isValid()) {
            energyProduction = new EnergyProduction();
            float amountToProduce = productionRequest.getAmountToProduce();
            energyProduction.addToAmount(amountToProduce);
            currentCapacity = ((amountToProduce / getMaxCapacity()) * 100);
        }
        // save production stats
        if(now.getTime() - start.getTime() > SmartGridVariables.PRODUCTION_INSERTS_STATS) {
            energyProduction.save(now);
            energyProduction.setStartDate(now);
            energyProduction.setEndDate(null);
        }
        // Update observers
        updateObservers();
    }

    public void registerObserver(ProductionSiteObservable observer) {
        observers.add(observer);
    }

    public void unregisterObserver(ProductionSiteObservable observer) {
        observers.remove(observer);
    }

    void updateObservers() {
        observers.forEach(observer -> observer.update(this));
    }

    public float getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(long currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public boolean isProducing() {
        return isProducing;
    }

    public void setProducing(boolean producing) {
        isProducing = producing;
    }

    public EnergyProduction getEnergyProduction() {
        return energyProduction;
    }
}
