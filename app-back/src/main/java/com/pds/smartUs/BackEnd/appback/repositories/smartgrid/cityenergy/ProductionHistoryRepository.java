package com.pds.smartUs.BackEnd.appback.repositories.smartgrid.cityenergy;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.ProductionHistorical;
import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy.ProductionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionHistoryRepository extends JpaRepository<ProductionHistory, Integer> {

    @Query(value = "SELECT sum(amount_produced), production_time from energy_production_history e\n" +
            "inner join production_site p on e.producer_id = p.production_site_id\n" +
            "inner join energy_caracteristics ec on p.energy_caracs_id = ec.energy_caracs_id\n" +
            "where producer_type = 'SITE' AND  ec.energy_type= 'WIND' group by production_time", nativeQuery = true)
    List<?> getWindHistory();

    @Query(value = "SELECT sum(amount_produced), production_time from energy_production_history e\n" +
            "inner join production_site p on e.producer_id = p.production_site_id\n" +
            "inner join energy_caracteristics ec on p.energy_caracs_id = ec.energy_caracs_id\n" +
            "where producer_type = 'SITE' AND  ec.energy_type= 'SOLAR' group by production_time", nativeQuery = true)
    List<?> getSolarHistory();

    @Query(value = "SELECT sum(amount_produced), production_time from energy_production_history e\n" +
            "inner join production_site p on e.producer_id = p.production_site_id\n" +
            "inner join energy_caracteristics ec on p.energy_caracs_id = ec.energy_caracs_id\n" +
            "where producer_type = 'SITE' AND  ec.energy_type= 'HYDRAULIC' group by production_time", nativeQuery = true)
    List<?> getHydraulicHistory();

    @Query(value = "SELECT sum(amount_produced), production_time from energy_production_history e\n" +
            "inner join production_site p on e.producer_id = p.production_site_id\n" +
            "inner join energy_caracteristics ec on p.energy_caracs_id = ec.energy_caracs_id\n" +
            "where producer_type = 'SITE' AND  ec.energy_type= 'THERMAL' group by production_time", nativeQuery = true)
    List<?> getThermalHistory();
}
