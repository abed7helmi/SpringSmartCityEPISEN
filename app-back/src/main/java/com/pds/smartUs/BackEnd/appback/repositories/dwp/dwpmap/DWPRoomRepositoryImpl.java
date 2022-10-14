package com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmap;


import com.pds.smartUs.BackEnd.appback.entities.dwpmap.DWP_Room;
import com.pds.smartUs.BackEnd.appback.entities.dwpmap.Room;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = false)
public class DWPRoomRepositoryImpl implements DWPRoomRepositoryCustom{

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<Integer> getIdRoomByArea(int id_dwp, String area_type) {
        Query query = entityManager.createNativeQuery("SELECT id_room FROM DWP_Room WHERE id_dwp="+id_dwp
        +" AND area_type='"+area_type+"' ORDER BY id_dwproom");
        return query.getResultList();
    }

    @Override
    public List<Integer> getIdRoomById_dwp(int id_dwp) {
        Query query = entityManager.createNativeQuery("SELECT id_room FROM DWP_Room WHERE id_dwp="+id_dwp);
        return query.getResultList();
    }

    @Override
    public void deleteDWPRoomById(int id_dwp) {
        Query query = entityManager.createNativeQuery("DELETE FROM DWP_Room WHERE id_dwp="+id_dwp);
        query.executeUpdate();

    }

    @Override
    public DWP_Room getDWPRoomByIdRoom(int id_room) {
       // Query query=entityManager.createNativeQuery("SELECT * FROM DWP_Room WHERE id_room="+id_room);
        Query query = entityManager.createNativeQuery("SELECT * FROM DWP_Room WHERE id_room="+id_room, DWP_Room.class);
        return (DWP_Room) query.getSingleResult();
    }

    @Override
    public List<Room> getDWPRoomsLessThanPositionX(int position, String area_type, int id_dwp) {
        Query query = entityManager.createNativeQuery("SELECT r.id_room,r.width,r.height,r.room_name,r.room_type FROM DWP_Room dwp, Room r WHERE " +
                                                         "dwp.id_dwp="+id_dwp+" AND dwp.area_type='"+area_type+"' AND dwp.position <= "+position
                                                           + " AND r.id_room=dwp.id_room",Room.class);
        return query.getResultList();
    }

    @Override
    public DWP_Room getSingleDWPRoom(int id_dwp) {
        Query query = entityManager.createNativeQuery("SELECT DISTINCT * FROM DWP_Room WHERE id_dwp="+id_dwp, DWP_Room.class);
        return (DWP_Room) query.getResultList().get(0);
    }


}
