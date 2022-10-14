package pds.smartus.frontend.entities.habitation;


import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data

public class HouseRoom {

    Long idRoom;
    String roomName;
    int surface;
    int nbWindows;
    private List<Equipment> equipments = new ArrayList<>();
    private Habitation habitation;
}
