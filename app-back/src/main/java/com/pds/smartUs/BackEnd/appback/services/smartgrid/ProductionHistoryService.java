package com.pds.smartUs.BackEnd.appback.services.smartgrid;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.ProductionHistorical;
import com.pds.smartUs.BackEnd.appback.repositories.smartgrid.cityenergy.ProductionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionHistoryService {

    @Autowired
    private ProductionHistoryRepository productionHistoryRepository;

    public List<?> getWindHistory(){return (List<?>) productionHistoryRepository.getWindHistory();}

    public List<?> getSolarHistory(){return (List<?>) productionHistoryRepository.getSolarHistory();}

    public List<?> getHydraulicHistory(){return (List<?>) productionHistoryRepository.getHydraulicHistory();}

    public List<?> getThermalHistory(){return (List<?>) productionHistoryRepository.getThermalHistory();}


}
