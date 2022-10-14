package com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmap;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.DWP;
import com.pds.smartUs.BackEnd.appback.entities.dwpmap.DWP_Area;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DWPAreaRepositoryImpl implements DWPAreaRepositoryCustom{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<DWP_Area> getAreasByArchitecture(int architecture) {
        Query query = entityManager.createNativeQuery("SELECT * FROM dwp_area WHERE id_architecture="+architecture
                , DWP_Area.class);
        return query.getResultList();
    }
}
