package com.pds.smartUs.BackEnd.appback.entities.smartgridmix;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Component
public class ProductionHistory {

    private int sumProdForMonth;
    private String month;

    public ProductionHistory(int sumProdForMonth, String month) {
        this.sumProdForMonth = sumProdForMonth;
        this.month = month;
    }

    public ProductionHistory(){
    }

}
