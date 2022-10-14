package pds.smartus.frontend.services.dwp.dwpmap;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import pds.smartus.frontend.entities.dwp.dwpmap.*;
import pds.smartus.frontend.repositories.dwp.dwpmap.DWPMapProxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Service
public class DWPMapService {

    @Autowired
    private DWPMapProxy dwpMapProxy;
    private Map<Integer,Boolean> reconfigured= new HashMap<>();

    public Boolean isConfigured(int id_dwp){
        return dwpMapProxy.getDWP(id_dwp).isConfigured();
    }

    public List<Room> getRoomsByArea(String area_type, int id_dwp){
        List<Room> roomByAreaList = new ArrayList<>();
        List<Integer> listRoomId= dwpMapProxy.getListRoomIdByArea(id_dwp,area_type);
        for(int id: listRoomId){
            roomByAreaList.add(dwpMapProxy.getRoom(id));
        }

        return roomByAreaList;
    }


    public void setReconfigure(int id_dwp,boolean b) {
            reconfigured.putIfAbsent(id_dwp,b);
    }

    public boolean isReconfigure(int id_dwp) {

        if(reconfigured.containsKey(id_dwp)){
            return reconfigured.get(id_dwp);
        }
        else {
            return false;
        }
    }

    public void deleteAllRoomsByIdDWP(int id_dwp) {
        //DELETE FROM Room WHERE id_room=?
        //DELETE FROM DWP_Room WHERE id_dwp=?
        //SELECT id_room FROM DWP_Room WHERE id_dwp=?
        List<Integer> listRoomToDelete= dwpMapProxy.getListRoomIdByIdDWP(id_dwp);

        List<Integer> l = dwpMapProxy.deleteDWPRoomById(id_dwp);

        for(int id_room: listRoomToDelete){
            dwpMapProxy.deleteRoom(id_room);
        }

    }

    public void setConfigured(int id_dwp, boolean b) {
        dwpMapProxy.setDWPConfigured(b,id_dwp);
    }

    public void buildMap1(Object model, int id_dwp) {
        if(model instanceof Model) {
            Model md = (Model) model;
            List<Room> roomsList= new ArrayList();
            md.addAttribute("id_dwp",id_dwp);
            for(int i=1;i<13;i++){
                md.addAttribute("roomsAO"+i,getRoomsByArea("AO"+i,id_dwp));
                roomsList.addAll(getRoomsByArea("AO"+i,id_dwp));
            }

            md.addAttribute("rooms",roomsList);
        }
        if(model instanceof ModelMap) {
            ModelMap md = (ModelMap) model;
            List<Room> roomsList= new ArrayList();
            md.addAttribute("id_dwp",id_dwp);
            for(int i=1;i<13;i++){
                md.addAttribute("roomsAO"+i,getRoomsByArea("AO"+i,id_dwp));
                roomsList.addAll(getRoomsByArea("AO"+i,id_dwp));
            }

            md.addAttribute("rooms",roomsList);
        }

    }

    public void buildMap2(Object model, int id_dwp) {

        List<Room> roomsList= new ArrayList<Room>( getRoomsByArea("AO1",id_dwp));
        roomsList.addAll( getRoomsByArea("AO2",id_dwp));
        roomsList.addAll(getRoomsByArea("AO3",id_dwp));
        roomsList.addAll(getRoomsByArea("AO4",id_dwp));
        roomsList.addAll(getRoomsByArea("AO5",id_dwp));
        roomsList.addAll(getRoomsByArea("AO6",id_dwp));
        roomsList.addAll(getRoomsByArea("AU1",id_dwp));
        roomsList.addAll(getRoomsByArea("AU2",id_dwp));

         if(model instanceof Model){
             Model md = (Model) model;
             md.addAttribute("id_dwp",id_dwp);
             md.addAttribute("roomsAO1", getRoomsByArea("AO1",id_dwp));
             md.addAttribute("roomsAO2", getRoomsByArea("AO2",id_dwp));
             md.addAttribute("roomsAO3", getRoomsByArea("AO3",id_dwp));
             md.addAttribute("roomsAO4", getRoomsByArea("AO4",id_dwp));
             md.addAttribute("roomsAO5", getRoomsByArea("AO5",id_dwp));
             md.addAttribute("roomsAO6", getRoomsByArea("AO6",id_dwp));
             md.addAttribute("roomsU1",  getRoomsByArea("AU1",id_dwp));
             md.addAttribute("roomsU2",  getRoomsByArea("AU2",id_dwp));

             md.addAttribute("rooms",roomsList);
         }
        if(model instanceof ModelMap){
            ModelMap md = (ModelMap) model;
            md.addAttribute("id_dwp",id_dwp);
            md.addAttribute("roomsAO1", getRoomsByArea("AO1",id_dwp));
            md.addAttribute("roomsAO2", getRoomsByArea("AO2",id_dwp));
            md.addAttribute("roomsAO3", getRoomsByArea("AO3",id_dwp));
            md.addAttribute("roomsAO4", getRoomsByArea("AO4",id_dwp));
            md.addAttribute("roomsAO5", getRoomsByArea("AO5",id_dwp));
            md.addAttribute("roomsAO6", getRoomsByArea("AO6",id_dwp));
            md.addAttribute("roomsU1",  getRoomsByArea("AU1",id_dwp));
            md.addAttribute("roomsU2",  getRoomsByArea("AU2",id_dwp));

            md.addAttribute("rooms",roomsList);
        }


    }

    public void deleteDWPContent(int id_dwp) {

            deleteAllRoomsByIdDWP(id_dwp);
            setReconfigure(id_dwp,false);
            setConfigured(id_dwp,false);

    }

    public List<Building> getBuildings() {
        return dwpMapProxy.getBuildings();
    }

    public List<DWP> getDWPsByIdBuilding(int id_building) {

        return dwpMapProxy.getDWPsByIdBuilding(id_building);
    }
    public List<DWP> getDWPsByIdBuilding(int id_building,boolean configured) {

        return dwpMapProxy.getDWPsByIdBuilding(id_building,configured);
    }


    public int getFloorNumber(int id_dwp) {
        return dwpMapProxy.getDWP(id_dwp).getFloor_number();
    }

    public Building getBuildingById(int id_building) {
        return dwpMapProxy.getBuildingById(id_building);
    }

    public DWP_Room getSingleDWPRoom(int id_dwp) {
        return dwpMapProxy.getSingleDWPRoom(id_dwp);
    }

}
