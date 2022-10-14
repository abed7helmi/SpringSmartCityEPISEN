package pds.smartus.frontend.services.dwp.usemonitor;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.smartus.frontend.entities.dwp.usemonitor.DwpDevice;
import pds.smartus.frontend.repositories.dwp.usemonitor.DwpDeviceProxy;

@Data
@Service
public class DwpDeviceService {
    @Autowired
    private DwpDeviceProxy dwpDeviceProxy;

    /*public DwpDevice getEquipmentById(final int equipmentId){
        return dwpDeviceProxy.getEquipmentById(equipmentId);
    }

    public Iterable<DwpDevice> getEquipments() {
        return dwpDeviceProxy.getEquipments();
    }*/

    public Iterable<DwpDevice> getEquipmentsbyRoom(int idroom) {
        return dwpDeviceProxy.getEquipmentByRoom(idroom);
    }

    public Integer getPC(int idroom){
        return dwpDeviceProxy.getPC(idroom);
    }
}
