package com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DwpRoomConsumption;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.DwpDeviceConsumptionRepository;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.DwpRoomConsumptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DwpRoomConsumptionService {
    private final DwpRoomConsumptionRepository dwpRoomConsumptionRepository;

    public DwpRoomConsumptionService(DwpRoomConsumptionRepository dwpRoomConsumptionRepository) {
        this.dwpRoomConsumptionRepository = dwpRoomConsumptionRepository;
    }

    public Float getSumConsumption(int roomId){
        return dwpRoomConsumptionRepository.getSumConsumption(roomId);
    }

    public List<Object> getInfosConsoRooms() {
        return dwpRoomConsumptionRepository.gestInfosConsoRooms();
    }

    public void saveRoomConsumption(DwpRoomConsumption dwpRoomConsumption) {
        dwpRoomConsumptionRepository.save(dwpRoomConsumption);
    }
}
