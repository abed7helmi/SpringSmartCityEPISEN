package com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmap;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.DWP;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DWPRepositoryImpl implements DWPRepositoryCustom{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<DWP> getDWPByIdBuilding(int id_building) {
        Query query = entityManager.createNativeQuery("SELECT * FROM DWP WHERE id_building="+id_building+" ORDER BY floor_number"
                                                        ,DWP.class);
        return query.getResultList();
    }
    public List<DWP> getDWPByIdBuilding(int id_building,boolean configured) {
        Query query = entityManager.createNativeQuery("SELECT * FROM DWP WHERE id_building="+id_building+
                                                    " AND configured="+configured+" ORDER BY floor_number",DWP.class);
        return query.getResultList();
    }

}

