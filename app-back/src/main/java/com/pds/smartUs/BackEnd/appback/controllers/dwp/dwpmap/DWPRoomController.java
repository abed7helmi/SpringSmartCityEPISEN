package com.pds.smartUs.BackEnd.appback.controllers.dwp.dwpmap;


import com.pds.smartUs.BackEnd.appback.entities.dwpmap.DWP_Room;
import com.pds.smartUs.BackEnd.appback.entities.dwpmap.Room;
import com.pds.smartUs.BackEnd.appback.services.dwp.dwpmap.DWPRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class DWPRoomController {

        private final DWPRoomService dwpRoomService;

        @Autowired
        public DWPRoomController(DWPRoomService dwpRoomService){
                this.dwpRoomService=dwpRoomService;
        }

        @PostMapping("/dwproom")
        public void addDWPRoom(@RequestBody DWP_Room dwp_room){
                dwpRoomService.addDWPRoom(dwp_room);
        }

        @PostMapping(value = "/dwp/configMap/{id_dwp}/{archi}", consumes="application/json")
        public void configMap(@RequestBody Map<String,List<Map<String,Object>>> map, @PathVariable("id_dwp") int id_dwp, @PathVariable("archi") int archi){
                dwpRoomService.configMap(map,id_dwp,archi);
        }

        @GetMapping("/dwproom/{id_dwp}/{area_type}")
        public List<Integer> getIdRoomByArea(@PathVariable("id_dwp") int id_dwp,@PathVariable("area_type") String area_type) {
                return dwpRoomService.getIdRoomsByArea(id_dwp,area_type);
        }

        @GetMapping("/dwproom/{id_dwp}")
        public List<Integer> getIdRoomByID_DWP(@PathVariable("id_dwp") int id_dwp) {
                return dwpRoomService.getIdRoomsByID_dwp(id_dwp);
        }
        @GetMapping("/dwproom/position/{id_room}")
        public DWP_Room getDWPRoomByIdRoom(@PathVariable("id_room") int id_room){
                return dwpRoomService.getDWPRoomByIdRoom(id_room);
        }

        @DeleteMapping("/dwproom/{id_dwp}")
        public void deleteDWPRoomById(@PathVariable("id_dwp") int id_dwp){
              dwpRoomService.deleteDWPRoomById(id_dwp);
        }
        @GetMapping("/dwppath/coordinates/{id_room_from}/{id_room_to}")
        public List getCoordinatesRooms(@PathVariable("id_room_from") int id_room_from, @PathVariable("id_room_to") int id_room_to){
                return dwpRoomService.getCoordinatesRooms(id_room_from,id_room_to);
        }

        @GetMapping("/dwproom/single/{id_dwp}")
        public DWP_Room getSingleDWPRoom(@PathVariable("id_dwp") int id_dwp){
                return dwpRoomService.getSingleDWPRoom(id_dwp);
        }
}
