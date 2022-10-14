package pds.smartus.frontend.entities.dwp.dwpmeetingroom;

import pds.smartus.frontend.entities.dwp.dwpmap.Room;

public class RoomDetector {
    private final RoomStatus room;
    private final Detector detector;

    public RoomDetector(RoomStatus room, Detector detector) {
        this.room = room;
        this.detector = detector;
    }


    public boolean getStatus() {
        return room.getStatus();
    }


    public Room getRoom() {
        return room.getRoom();
    }

    public int getDetector_id() {
        return detector.id;
    }

}
