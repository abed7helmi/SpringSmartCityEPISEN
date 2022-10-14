package com.pds.smartUs.BackEnd.appback.services.dwp.dwpmap;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.DWP;
import com.pds.smartUs.BackEnd.appback.entities.dwpmap.DWP_Area;
import com.pds.smartUs.BackEnd.appback.entities.dwpmap.DWP_Room;
import com.pds.smartUs.BackEnd.appback.entities.dwpmap.Room;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmap.DWPAreaRepository;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmap.DWPRepository;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmap.DWPRoomRepository;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmap.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class DWPRoomService {

    private final DWPRoomRepository dwpRoomRepository;
    private final DWPAreaRepository dwpAreaRepository;
    private final RoomRepository roomRepository;
    private final DWPRepository dwpRepository;
    @Autowired
    public DWPRoomService(DWPRoomRepository dwpRoomRepository, DWPAreaRepository dwpAreaRepository, RoomRepository roomRepository, DWPRepository dwpRepository){
        this.dwpRoomRepository=dwpRoomRepository;
        this.dwpAreaRepository = dwpAreaRepository;

        this.roomRepository = roomRepository;
        this.dwpRepository = dwpRepository;
    }

    public void addDWPRoom(DWP_Room dwproom){
        dwpRoomRepository.save(dwproom);
    }

    public List<Integer> getIdRoomsByArea(int id_dwp, String area_type){
        return dwpRoomRepository.getIdRoomByArea(id_dwp,area_type);
    }

    public List<Integer> getIdRoomsByID_dwp(int id_dwp) {
        return dwpRoomRepository.getIdRoomById_dwp(id_dwp);
    }

    public void deleteDWPRoomById(int id_dwp) {
         dwpRoomRepository.deleteDWPRoomById(id_dwp);
    }

    public DWP_Room getDWPRoomByIdRoom(int id_room) {
        return dwpRoomRepository.getDWPRoomByIdRoom(id_room);
    }

    public float calculateCoordinate(List<Room> dwp_roomList,DWP_Room dwproom, Map infos_room){
        float coordinates = 0;
        for(Room r: dwp_roomList){

                    if(r.getId_room()==dwproom.getId_room()){
                        coordinates += (float) r.getWidth()/2;
                        infos_room.put("width",r.getWidth());
                        infos_room.put("height",r.getHeight());
                    }
                    else {
                        coordinates += r.getWidth();
                    }

        }

        return coordinates;
    }
    public List getCoordinatesRooms(int id_room_from, int id_room_to) {
        List<Map<String,Object>> resultMapList = new ArrayList<>();
        Map<String,Object> infos_room_from = new HashMap<>();
        DWP_Room dwproom = dwpRoomRepository.getDWPRoomByIdRoom(id_room_from);
        DWP_Area dwparea = dwpAreaRepository.findById(dwproom.getId_dwp_area()).get();
        infos_room_from.put("area_type", dwparea.getLabel());
        infos_room_from.put("archi", dwparea.getId_architecture());
        infos_room_from.put("id_hallway", dwparea.getId_hallway());
        infos_room_from.put("area_x", dwparea.getX());
        infos_room_from.put("area_y", dwparea.getY());
        infos_room_from.put("position", dwproom.getPosition());
        infos_room_from.put("direction", dwparea.getDirection());
        List<Room> dwp_roomList_from = dwpRoomRepository.getDWPRoomsLessThanPositionX(dwproom.getPosition(),dwparea.getLabel(),dwproom.getId_dwp());
        infos_room_from.put("coordinate",calculateCoordinate(dwp_roomList_from,dwproom,infos_room_from));

        Map<String,Object> infos_room_to = new HashMap<>();
        dwproom = dwpRoomRepository.getDWPRoomByIdRoom(id_room_to);
        dwparea = dwpAreaRepository.findById(dwproom.getId_dwp_area()).get();
        infos_room_to.put("position", dwproom.getPosition());
        infos_room_to.put("area_type", dwparea.getLabel());
        infos_room_to.put("archi", dwparea.getId_architecture());
        infos_room_to.put("id_hallway", dwparea.getId_hallway());
        infos_room_to.put("area_x", dwparea.getX());
        infos_room_to.put("area_y", dwparea.getY());
        infos_room_to.put("direction", dwparea.getDirection());
        List<Room> dwp_roomList_to = dwpRoomRepository.getDWPRoomsLessThanPositionX(dwproom.getPosition(),dwparea.getLabel(),dwproom.getId_dwp());
        infos_room_to.put("coordinate",calculateCoordinate(dwp_roomList_to,dwproom,infos_room_to));

        resultMapList.add(infos_room_from);
        resultMapList.add(infos_room_to);

        return resultMapList;
    }

    public DWP_Room getSingleDWPRoom(int id_dwp) {
        return dwpRoomRepository.getSingleDWPRoom(id_dwp);
    }

    @Transactional
    public void configMap(Map<String, List<Map<String,Object>>> map, int id_dwp, int archi) {
        boolean configured = dwpRepository.findById(id_dwp).get().isConfigured();

        List<Map<String,Object>> roomList = null;
        for(String areaLabel: map.keySet()){
            roomList = map.get(areaLabel);

            for(Map<String,Object> room: roomList){

                Room r = roomRepository.save(new Room(
                                            room.get("type_room").toString(),
                                            room.get("name-room").toString(),
                                            Integer.parseInt(room.get("width").toString()),
                                            Integer.parseInt(room.get("height").toString())
                                            )
                                    );

                addDWPRoom(new DWP_Room(
                        id_dwp,
                        archi,
                        areaLabel.toUpperCase(),
                        r.getId_room(),
                        Integer.parseInt(room.get("position").toString()),
                        Integer.parseInt(room.get("id_hallway").toString()),
                        Integer.parseInt(room.get("id_dwp_area").toString())
                ));
            }
        }

        if(!configured){
            DWP dwp= dwpRepository.findById(id_dwp).orElseThrow(() -> new IllegalStateException(
                    "dwp with id " + id_dwp + " does not exist"));
            dwp.setConfigured(true);
        }
    }
}
