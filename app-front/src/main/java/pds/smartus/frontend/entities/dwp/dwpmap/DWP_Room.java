package pds.smartus.frontend.entities.dwp.dwpmap;

public class DWP_Room {

    int id_dwproom;
    int id_dwp;
    int archi_type;
    String area_type;
    int id_room;
    int position;
    int id_hallway;
    int id_dwp_area;
    public DWP_Room(){}

    public DWP_Room(int id_dwp, int archi_type, String area_type, int id_room,int position, int id_hallway, int id_dwp_area) {
        this.id_dwp = id_dwp;
        this.archi_type = archi_type;
        this.area_type = area_type;
        this.id_room = id_room;
        this.position = position;
        this.id_hallway = id_hallway;
        this.id_dwp_area = id_dwp_area;
    }

    public int getId_dwp() {
        return id_dwp;
    }

    public void setId_dwp(int id_dwp) {
        this.id_dwp = id_dwp;
    }

    public int getArchi_type() {
        return archi_type;
    }

    public void setArchi_type(int archi_type) {
        this.archi_type = archi_type;
    }

    public String getArea_type() {
        return area_type;
    }

    public void setArea_type(String area_type) {
        this.area_type = area_type;
    }

    public int getId_room() {
        return id_room;
    }

    public void setId_room(int id_room) {
        this.id_room = id_room;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getId_hallway() {
        return id_hallway;
    }

    public void setId_hallway(int id_hallway) {
        this.id_hallway = id_hallway;
    }

    public int getId_dwp_area() {
        return id_dwp_area;
    }

    public void setId_dwp_area(int id_dwp_area) {
        this.id_dwp_area = id_dwp_area;
    }
}
