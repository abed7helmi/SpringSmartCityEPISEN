package com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.ElectricLuminosityRoom;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.ElectricLumiRepository;
import org.springframework.stereotype.Service;

@Service
public class ElectricLumiService {

    private final ElectricLumiRepository electricLumiRepository;

    public ElectricLumiService(ElectricLumiRepository electricLumiRepository) {
        this.electricLumiRepository = electricLumiRepository;
    }

    public void useLight(ElectricLuminosityRoom electricLuminosityRoom) {
       electricLumiRepository.save(electricLuminosityRoom);
    }

}
