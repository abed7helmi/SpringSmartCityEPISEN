package com.pds.smartUs.BackEnd.appback.entities.smartgridmix;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductionHistorical {

    private int sumProdForMonth;
    private String month;

}
