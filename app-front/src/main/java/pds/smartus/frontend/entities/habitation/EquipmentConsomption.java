package pds.smartus.frontend.entities.habitation;


import lombok.Data;

import java.util.Date;


@Data

public class EquipmentConsomption {

    private Long id;
    private Date time;
    private double consomption;
    private Equipment equipment;
}
