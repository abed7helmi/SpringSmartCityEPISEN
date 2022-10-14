package com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmap;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.DWP_Room;
import com.pds.smartUs.BackEnd.appback.entities.dwpmap.Room;

import java.util.List;

public interface DWPRoomRepositoryCustom {

    List<Integer> getIdRoomByArea(int id_dwp, String area_type);
    List<Integer> getIdRoomById_dwp(int id_dwp);

    void deleteDWPRoomById(int id_dwp);

    DWP_Room getDWPRoomByIdRoom(int id_room);

    List<Room> getDWPRoomsLessThanPositionX(int position, String area_type, int id_dwp);

    DWP_Room getSingleDWPRoom(int id_dwp);
}
