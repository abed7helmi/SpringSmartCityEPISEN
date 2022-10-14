package com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.SensorTempRepository;
import org.springframework.stereotype.Service;

@Service
public class SensorTempService {

    private final SensorTempRepository sensorTempRepository;

    public SensorTempService(SensorTempRepository sensorTempRepository) {
        this.sensorTempRepository = sensorTempRepository;
    }

    public String getLastTemp(int id_room){
        return sensorTempRepository.lastTempRoom(id_room);
    }

    public String getLastDate(int id_room) {
        return sensorTempRepository.lastDateTemp(id_room);
    }

}
