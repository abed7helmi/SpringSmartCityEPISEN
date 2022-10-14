package pds.smartus.frontend.entities.dwp.dwpmap;

public class Room {
    int id_room;
    String room_type;
    int width;
    int height;
    String room_name;
    public Room() {

    }
    public Room(int id_room, String room_type, int width, int height) {
        this.id_room = id_room;
        this.room_type = room_type;
        this.width = width;
        this.height = height;

    }
    public Room(int id_room,String room_type, int width, int height, String room_name) {
        this.id_room = id_room;
        this.room_type = room_type;
        this.width = width;
        this.height = height;
        this.room_name=room_name;
    }
    public Room(String room_type, int width, int height) {
        this.id_room = id_room;
        this.room_type = room_type;
        this.width = width;
        this.height = height;
        room_name=room_type+"_"+id_room;
    }

    public int getId_room() {
        return id_room;
    }

    public void setId_room(int id_room) {
        this.id_room = id_room;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getWidthInPixel() {
        return (width*160)/5;
    }
}
