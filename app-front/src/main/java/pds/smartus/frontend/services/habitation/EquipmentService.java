package pds.smartus.frontend.services.habitation;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.smartus.frontend.entities.habitation.Equipment;
import pds.smartus.frontend.repositories.habitation.EquipmentProxy;

@Data
@Service
public class EquipmentService {

    @Autowired
    private EquipmentProxy equipmentProxy;


    public Iterable<Equipment> getEquipments(Long roomid){

        return equipmentProxy.getEquipments(roomid);

    }
}
