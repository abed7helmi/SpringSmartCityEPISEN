package pds.smartus.frontend.entities.habitation;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data

public class Habitation {

    Long idHabitation;
    int zipCode;
    String streetName;
    String streetNumber;
    String HabitationType;
    boolean isActive;

    private List<HouseRoom> houseRooms = new ArrayList<>();
    private Bepos bepos;
    private Inhabitant inhabitant;
}
