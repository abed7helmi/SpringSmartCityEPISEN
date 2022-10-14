package com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DwpDevice;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.DwpDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class DwpDeviceService {
    private final DwpDeviceRepository dwpDeviceRepository;

    @Autowired
    public DwpDeviceService(DwpDeviceRepository dwpDeviceRepository) {
        this.dwpDeviceRepository = dwpDeviceRepository;
    }


    public List<DwpDevice> getEquipments(){
        return (List<DwpDevice>) dwpDeviceRepository.findAll();
    }

    public List<DwpDevice> getEquipmentsbyRoom(int idroom){
        return (List<DwpDevice>) dwpDeviceRepository.equipmentsByRoom(idroom);
    }

    public int getPC(int idroom) {return dwpDeviceRepository.getIdPC(idroom);}

    public DwpDevice getEquipmentById(int equipmentId){
        return dwpDeviceRepository.findById(equipmentId).get();
    }

    public void deleteEquipment(final Integer id) {
        dwpDeviceRepository.deleteById(id);
    }

    public void addNewEquipment(DwpDevice dwpDevice) {
        dwpDeviceRepository.save(dwpDevice);
    }

    @Transactional
    public void updateEquipment(int equipmentId, String name, String type) {
        DwpDevice dwpDevice = dwpDeviceRepository.findById(equipmentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Equipment with id" + equipmentId + "does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(dwpDevice.getName(), name)){
            dwpDevice.setName(name);
        }

        if (type != null && type.length() > 0 && !Objects.equals(dwpDevice.getType(), type)){
            dwpDevice.setType(type);
        }

    }


}
