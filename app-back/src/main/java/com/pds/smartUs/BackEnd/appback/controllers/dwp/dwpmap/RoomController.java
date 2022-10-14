package com.pds.smartUs.BackEnd.appback.controllers.dwp.dwpmap;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.Room;
import com.pds.smartUs.BackEnd.appback.services.dwp.dwpmap.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService){
        this.roomService=roomService;
    }

    @PostMapping("/room")
    public Room addRoom(@RequestBody Room room){
        return roomService.addRoom(room);
    }

    @GetMapping("/roombyarea/{id_room}")
    public Room getRoomsById(@PathVariable("id_room") int id_room){
        return roomService.getRoomById(id_room);
    }

    @DeleteMapping("/room/{id_room}")
    public void deleteRoomById(@PathVariable("id_room") int id_room){
        roomService.deleteRoomById(id_room);
    }

    @DeleteMapping("/room/deleteAll/{id_dwp}")
    public void deleteRoomsById_dwp(@PathVariable("id_dwp") int id_dwp){
        roomService.deleteRoomsById_dwp(id_dwp);
    }
}
