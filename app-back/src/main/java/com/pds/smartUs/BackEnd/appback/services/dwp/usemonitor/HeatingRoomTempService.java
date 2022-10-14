package com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.HeatingRoomTemperature;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.HeatingRoomTempRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeatingRoomTempService {
    private final HeatingRoomTempRepository heatingRoomTempRepository;

    public HeatingRoomTempService(HeatingRoomTempRepository heatingRoomTempRepository) {
        this.heatingRoomTempRepository = heatingRoomTempRepository;
    }

    public List<HeatingRoomTemperature> getAllHeatingInfos() {
        return (List<HeatingRoomTemperature>) heatingRoomTempRepository.findAll();
    }

    public Boolean getHeatingState(int idroom) {
        return heatingRoomTempRepository.heating_state(idroom);
    }

    public Integer getLastDesiredTemp(int idroom) {
        return heatingRoomTempRepository.lastDesiredTemp(idroom);
    }

    /*public void turnOffHeating(HeatingRoomTemperature heatingRoomTemperature) {
        *//*heatingRoomTempRepository.heatingOff(state, idroom);*//*
        heatingRoomTempRepository.save(heatingRoomTemperature);
    }*/

    public void useHeating(HeatingRoomTemperature heatingRoomTemperature) {
        heatingRoomTempRepository.save(heatingRoomTemperature);
    }

}
