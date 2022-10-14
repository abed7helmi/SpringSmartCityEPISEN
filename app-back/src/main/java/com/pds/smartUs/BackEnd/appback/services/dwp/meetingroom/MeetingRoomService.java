package com.pds.smartUs.BackEnd.appback.services.dwp.meetingroom;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.Room;
import com.pds.smartUs.BackEnd.appback.entities.dwpmeetingroom.Detector;
import com.pds.smartUs.BackEnd.appback.entities.dwpmeetingroom.MeetingRoomActivity;
import com.pds.smartUs.BackEnd.appback.models.RoomStatus;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmap.RoomRepository;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmeetingroom.DetectorRepository;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmeetingroom.MeetingRoomActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service
public class MeetingRoomService {
    private final MeetingRoomActivityRepository meetingRoomActivityRepository;
    private final RoomRepository roomRepository;
    private final DetectorRepository detectorRepository;
    private final ReservationService reservationService;

    @Autowired
    public MeetingRoomService(MeetingRoomActivityRepository meetingRoomActivityRepository, RoomRepository roomRepository, DetectorRepository detectorRepository, ReservationService reservationService) {
        this.meetingRoomActivityRepository = meetingRoomActivityRepository;
        this.roomRepository = roomRepository;
        this.detectorRepository = detectorRepository;
        this.reservationService = reservationService;
    }

    public Iterable<MeetingRoomActivity> getMeetingRoomActivities() {
        return meetingRoomActivityRepository.findAll();
    }

    public final List<Room> getRoomHasNotDetector() {
        List<Room> rooms = new ArrayList<>();
        Iterable<Room> roomIterable = roomRepository.findAll();
        for(Room room: roomIterable) {
            if(room.getRoom_type().toLowerCase(Locale.ROOT).equals("sr")) {
                Optional<Detector> detector = detectorRepository.findByRoom_id(room.getId_room());
                if(detector.isEmpty()) {
                    rooms.add(room);
                }
            }
        }
        return rooms;
    }

    public final List<RoomStatus> getMeetingRoomsStatus() {
        List<RoomStatus> meetingRoomsStatus = new ArrayList<>();
        Iterable<Room> roomIterable = roomRepository.findAll();
        for(Room room: roomIterable) {
            if(room.getRoom_type().toLowerCase(Locale.ROOT).equals("sr")) {
                try {
                    meetingRoomsStatus.add(new RoomStatus(room, reservationService.isAvailableNow(room.getId_room())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return meetingRoomsStatus;
    }

    public final Detector getRoomByDetector(Room room) {
        Detector elseD = new Detector();
        elseD.setId(0);
        return detectorRepository.findByRoom_id(room.getId_room()).orElse(elseD);
    }

}
