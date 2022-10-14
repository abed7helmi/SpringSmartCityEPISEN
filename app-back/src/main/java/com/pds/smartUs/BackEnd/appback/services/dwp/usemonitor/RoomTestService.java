package com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.DWP_Room;
import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.RoomTest;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.RoomTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoomTestService {

    private final RoomTestRepository roomRepository;

    @Autowired
    public RoomTestService(RoomTestRepository roomRepository){
        this.roomRepository=roomRepository;
    }

    public RoomTest getRoomById(int id_room) {
        return roomRepository.findById(id_room).get();
    }


    public void deleteRoomById(int id_room) {
        roomRepository.deleteById(id_room);
    }

}
