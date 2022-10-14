package pds.smartus.frontend.entities.habitation;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;


@Data

public class Equipment {

    private Long id;
    private String type;
    private String name;
    private String power;

    private List<EquipmentConsomption> equipmentConsomptions = new ArrayList<>();
    private HouseRoom houseroom;



}

