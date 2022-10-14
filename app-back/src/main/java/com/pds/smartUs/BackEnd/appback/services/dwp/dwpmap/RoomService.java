package com.pds.smartUs.BackEnd.appback.services.dwp.dwpmap;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.DWP_Room;
import com.pds.smartUs.BackEnd.appback.entities.dwpmap.Room;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmap.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final DWPRoomService dwpRoomService;


    @Autowired
    public RoomService(RoomRepository roomRepository, DWPRoomService dwpRoomService){
        this.roomRepository=roomRepository;
        this.dwpRoomService = dwpRoomService;
    }

    public Room addRoom(Room room){

        return roomRepository.save(room);

    }


    public Room getRoomById(int id_room) {
        return roomRepository.findById(id_room).get();
    }


    public void deleteRoomById(int id_room) {
        roomRepository.deleteById(id_room);
    }

    public void restore() {
        List<Room> list = (List<Room>) roomRepository.findAll();
        int position = 1;
        for(Room r: list){
            if(266<= r.getId_room() && r.getId_room() <= 272){
                dwpRoomService.addDWPRoom(new DWP_Room(
                        2,
                        2,
                        "AO1",
                        r.getId_room(),
                        position,
                        2,
                        1
                ));
                position++;
                if(r.getId_room()==272){
                    position = 1;
                }
            }

            if(273<= r.getId_room() && r.getId_room() <= 276){
                dwpRoomService.addDWPRoom(new DWP_Room(
                        2,
                        2,
                        "AO2",
                        r.getId_room(),
                        position,
                        1,
                        2
                ));
                position++;
                if(r.getId_room()==276){
                    position = 1;
                }
            }

            if(277<= r.getId_room() && r.getId_room() <= 279){
                dwpRoomService.addDWPRoom(new DWP_Room(
                        2,
                        2,
                        "AO3",
                        r.getId_room(),
                        position,
                        8,
                        3
                ));
                position++;
                if(r.getId_room()==279){
                    position = 1;
                }
            }

            if(280<= r.getId_room() && r.getId_room() <= 283){
                dwpRoomService.addDWPRoom(new DWP_Room(
                        2,
                        2,
                        "AO4",
                        r.getId_room(),
                        position,
                        5
                        ,4
                ));
                position++;
                if(r.getId_room()==283){
                    position = 1;
                }
            }

            if(284<= r.getId_room() && r.getId_room() <= 285){
                dwpRoomService.addDWPRoom(new DWP_Room(
                        2,
                        2,
                        "AO5",
                        r.getId_room(),
                        position,
                        5,
                        5
                ));
                position++;
                if(r.getId_room()==285){
                    position = 1;
                }
            }

            if(286<= r.getId_room() && r.getId_room() <= 291){
                dwpRoomService.addDWPRoom(new DWP_Room(
                        2,
                        2,
                        "AO6",
                        r.getId_room(),
                        position,
                        6,6
                ));
                position++;
                if(r.getId_room()==291){
                    position = 1;
                }
            }

            if(292<= r.getId_room() && r.getId_room() <= 294){
                dwpRoomService.addDWPRoom(new DWP_Room(
                        2,
                        2,
                        "U1",
                        r.getId_room(),
                        position,
                        7,
                        7
                ));
                position++;
                if(r.getId_room()==294){
                    position = 1;
                }
            }

            if(r.getId_room()==295){
                dwpRoomService.addDWPRoom(new DWP_Room(
                        2,
                        2,
                        "U2",
                        r.getId_room(),
                        position,
                        1,
                        8
                ));
                position++;
            }
        }

    }

    public void deleteRoomsById_dwp(int id_dwp) {
        roomRepository.deleteRoomsById_dwp(id_dwp);
    }
}
