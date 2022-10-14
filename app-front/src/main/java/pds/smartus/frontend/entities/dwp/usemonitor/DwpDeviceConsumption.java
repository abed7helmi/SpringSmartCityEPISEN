package pds.smartus.frontend.entities.dwp.usemonitor;

import lombok.Data;

import javax.persistence.*;

@Data
public class DwpDeviceConsumption {

    int id_cons;

    Float consumption;
    String date_conso;
    int device_id;

}
