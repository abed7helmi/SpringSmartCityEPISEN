package pds.smartus.frontend.repositories.dwp.meetingroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pds.smartus.frontend.entities.dwp.dwpmap.Room;
import pds.smartus.frontend.entities.dwp.dwpmeetingroom.Detector;
import pds.smartus.frontend.entities.dwp.dwpmeetingroom.MeetingRoomActivity;
import pds.smartus.frontend.entities.dwp.dwpmeetingroom.RoomStatus;
import pds.smartus.frontend.entities.dwp.usemonitor.Reservation;
import pds.smartus.frontend.properties.CustomProperties;

import java.util.List;
import java.util.Map;

@Service
public class MeetingProxy {
    @Autowired
    private CustomProperties properties;

    public Iterable<MeetingRoomActivity> patchAlerts() {
        String getReservationsUrl = properties.getApiUrl()+"/meeting_room/alerts";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<MeetingRoomActivity>> response = restTemplate.exchange(
                getReservationsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    public List<Room> patchRoomHasNotDetector() {
        String getReservationsUrl = properties.getApiUrl()+"/meeting_room/rooms_without_detector";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Room>> response = restTemplate.exchange(
                getReservationsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    public List<RoomStatus> patchMeetingRoomsStatus() {
        String getReservationsUrl = properties.getApiUrl()+"/meeting_room/rooms_status";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<RoomStatus>> response = restTemplate.exchange(
                getReservationsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    public Detector patchDetectorByRoom(Room room) {
        String getReservationsUrl = properties.getApiUrl()+"/meeting_room/room_detector/" + room.getId_room();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Detector> response = restTemplate.exchange(
                getReservationsUrl,
                HttpMethod.GET,
                null,
                Detector.class
        );
        return response.getBody();
    }
}
