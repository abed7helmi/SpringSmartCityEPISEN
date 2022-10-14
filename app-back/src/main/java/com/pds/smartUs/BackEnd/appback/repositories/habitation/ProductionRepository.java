package com.pds.smartUs.BackEnd.appback.repositories.habitation;


import com.pds.smartUs.BackEnd.appback.entities.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ProductionRepository extends JpaRepository<Production,Long> {

    @Query(value = "SELECT distinct(time) from production order by time desc limit 10 ;)", nativeQuery = true)
    public Iterable<Production> getTime();

    @Query(value = "SELECT pp.* from producteur p INNER JOIN production pp on p.id = pp.producteur_id where p.bepos_id = ?2 order by time desc limit ?1 ;",nativeQuery = true)
    public List<Production> findProductionsByTime(int limit,Long beposid);


    @Query(value = "SELECT sum(pp.production) from producteur p INNER JOIN production pp on p.id = pp.producteur_id where p.bepos_id = ?1"+" and pp.time > ?2"+" and pp.time < ?3 ; ",nativeQuery = true)
    public Double findProductionsByDate(Long beposid , LocalDate yes2,LocalDate minuittotime);

}
