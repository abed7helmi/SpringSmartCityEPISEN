package pds.smartus.frontend.controllers.dwp.meetingroom;

import lombok.Data;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pds.smartus.frontend.entities.dwp.dwpmeetingroom.MeetingRoomActivity;
import pds.smartus.frontend.entities.dwp.dwpmeetingroom.RoomDetector;
import pds.smartus.frontend.repositories.dwp.meetingroom.MeetingProxy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class MeetingController {
    private final MeetingProxy meetingProxy;
    private final static PrettyTime PRETTY_TIME = new PrettyTime(new Locale("fr"));
    private final static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Autowired
    public MeetingController(MeetingProxy meetingProxy) {
        this.meetingProxy = meetingProxy;
    }

    @GetMapping("/meeting_room")
    public String getMeetingRoomsInfo(Model md) {
        md.addAttribute("roomStatus", meetingProxy.patchMeetingRoomsStatus().
                stream().map(room -> new RoomDetector(room, meetingProxy.patchDetectorByRoom(room.getRoom()))).toList());
        md.addAttribute("withoutDetector", meetingProxy.patchRoomHasNotDetector());
        Iterable<MeetingRoomActivity> attributeValue = meetingProxy.patchAlerts();
        setAlertTime(attributeValue);
        md.addAttribute("proxy", meetingProxy);
        md.addAttribute("alerts", attributeValue);
        return "dwp/meetingroom/index";
    }


    private void setAlertTime(Iterable<MeetingRoomActivity> alertTime) {
        alertTime.forEach(x -> {
            try {
                x.setDuration(PRETTY_TIME.format(formatter.parse(x.getDate() + " " + x.getHour())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }
}
