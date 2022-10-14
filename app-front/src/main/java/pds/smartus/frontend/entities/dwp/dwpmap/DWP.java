package pds.smartus.frontend.entities.dwp.dwpmap;

public class DWP {

    int id_dwp;

    boolean configured;

    int floor_number;
    int id_building;

    public DWP(){

    }

    public DWP(int id_dwp, boolean configured, int floor_number,int id_building){
        this.id_dwp=id_dwp;
        this.configured=configured;
        this.floor_number = floor_number;
        this.id_building = id_building;
    }

    public int getFloor_number() {
        return floor_number;
    }

    public void setFloor_number(int floor_number) {
        this.floor_number = floor_number;
    }

    public int getId_building() {
        return id_building;
    }

    public void setId_building(int id_building) {
        this.id_building = id_building;
    }

    public int getId_dwp() {
        return id_dwp;
    }

    public void setId_dwp(int id_dwp) {
        this.id_dwp = id_dwp;
    }

    public boolean isConfigured() {
        return configured;
    }

    public void setConfigured(boolean configured) {
        this.configured = configured;
    }
}
