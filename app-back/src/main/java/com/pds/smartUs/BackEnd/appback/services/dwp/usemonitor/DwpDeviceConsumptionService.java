package com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DwpDeviceConsumption;
import com.pds.smartUs.BackEnd.appback.helpers.Helper;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.DwpDeviceConsumptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DwpDeviceConsumptionService {
    private final DwpDeviceConsumptionRepository dwpDeviceConsumptionRepository;

    public DwpDeviceConsumptionService(DwpDeviceConsumptionRepository dwpDeviceConsumptionRepository) {
        this.dwpDeviceConsumptionRepository = dwpDeviceConsumptionRepository;
    }

    public void addConsoForCafe(int deviceId) {
        DwpDeviceConsumption dwpDeviceConsumption = dwpDeviceConsumptionRepository.findByDeviceId(deviceId);
        Float newConso = dwpDeviceConsumption.getConsumption()+50;
        dwpDeviceConsumption.setConsumption(newConso);
        dwpDeviceConsumption.setDate_conso(Helper.getDateNow());
        dwpDeviceConsumptionRepository.save(dwpDeviceConsumption);
    }

    public Iterable<DwpDeviceConsumption> getAllConso() {
        return dwpDeviceConsumptionRepository.findAll();
    }

    public List<Object> getinfosDevicesbyRoom(int roomId) {
        return dwpDeviceConsumptionRepository.gestInfosDevicebyRoom(roomId);
    }

    public Float getConsoByDeviceId(int eqId){
        return dwpDeviceConsumptionRepository.getConsoByDeviceId(eqId);
    }
}
