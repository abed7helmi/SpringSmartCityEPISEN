package com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.SensorLumiRepository;
import org.springframework.stereotype.Service;

@Service
public class SensorLumiService {

    private SensorLumiRepository sensorLumiRepository;

    public SensorLumiService(SensorLumiRepository sensorLumiRepository) {
        this.sensorLumiRepository = sensorLumiRepository;
    }

    public int getLastLumi(int idroom) {
        return sensorLumiRepository.lastLumiRoom(idroom);
    }

    public int getOutdoorLumi(int idroom) {
        return sensorLumiRepository.outdoorLumi(idroom);
    }
}
