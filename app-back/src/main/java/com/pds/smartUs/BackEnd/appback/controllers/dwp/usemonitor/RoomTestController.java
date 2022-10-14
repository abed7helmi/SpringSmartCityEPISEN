package com.pds.smartUs.BackEnd.appback.controllers.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.Room;
import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.RoomTest;
import com.pds.smartUs.BackEnd.appback.services.dwp.dwpmap.RoomService;
import com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor.RoomTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomTestController {
    private final RoomTestService roomService;

    @Autowired
    public RoomTestController(RoomTestService roomService){
        this.roomService=roomService;
    }

    @GetMapping("/getRoom/{id_room}")
    public RoomTest getRoomsById(@PathVariable("id_room") int id_room){
        return roomService.getRoomById(id_room);
    }
}
