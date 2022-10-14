package com.pds.smartUs.BackEnd.appback.controllers.dwp.meetingroom;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.Room;
import com.pds.smartUs.BackEnd.appback.entities.dwpmeetingroom.Detector;
import com.pds.smartUs.BackEnd.appback.entities.dwpmeetingroom.MeetingRoomActivity;
import com.pds.smartUs.BackEnd.appback.models.RoomStatus;
import com.pds.smartUs.BackEnd.appback.services.dwp.meetingroom.MeetingRoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/meeting_room")
public class MeetingRoomController {
    private final MeetingRoomService meetingRoomService;

    public MeetingRoomController(MeetingRoomService meetingRoomService) {
        this.meetingRoomService = meetingRoomService;
    }

    @GetMapping("/alerts")
    public Iterable<MeetingRoomActivity> patchAlerts() {
        return meetingRoomService.getMeetingRoomActivities();
    }

    @GetMapping("/rooms_without_detector")
    public List<Room> patchRoomHasNotDetector() {
        return  meetingRoomService.getRoomHasNotDetector();
    }

    @GetMapping("/rooms_status")
    public List<RoomStatus> patchMeetingRoomsStatus() {
        return meetingRoomService.getMeetingRoomsStatus();
    }

    @GetMapping("/room_detector/{roomid}")
    public Detector getDetectorByRoom(@PathVariable("roomid") Room room) {
        return meetingRoomService.getRoomByDetector(room);
    }
}
