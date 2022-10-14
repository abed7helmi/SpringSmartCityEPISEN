package com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmap;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@Transactional(readOnly = false)
public class RoomRepositoryImpl implements RoomRepositoryCustom{

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public void deleteRoomsById_dwp(int id_dwp) {
        Query query = entityManager.createNativeQuery("DELETE FROM Room WHERE id_room IN (SELECT id_room FROM dwp_room WHERE id_dwp="+id_dwp+")");
        query.executeUpdate();
    }
}
