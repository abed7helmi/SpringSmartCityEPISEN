package com.pds.smartUs.BackEnd.appback.services.habitation;

import com.pds.smartUs.BackEnd.appback.entities.habitation.DayParameters;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.LightParametersRepository;
import org.springframework.stereotype.Service;

@Service
public class LightParametersService {
    private static LightParametersRepository lightParametersRepository;



    public LightParametersService(LightParametersRepository lightParametersRepository) {
        this.lightParametersRepository = lightParametersRepository;
    }
    /*
        if((dayParameters.getCurrentsTime().getTime() > 22) || (dayParameters.getCurrentsTime().getTime() > 8)) {
            lightParametersRepository.switchOffLight(idLight);

    */
    public static void lightsControl(int idLight, DayParameters dayParameters) {
        if((dayParameters.getCurrentsTime().getHours() >= 19) && (dayParameters.getCurrentsTime().getHours() < 22)) {
            System.out.println("switch on");
            lightParametersRepository.switchOnLight(idLight);
        } else {
            System.out.println("switch off");
            lightParametersRepository.switchOffLight(idLight);
        }
    }

    public void switchOffLights(int idLight) {
        lightParametersRepository.switchOffLight(idLight);
    }


    public void switchOnLights(int idLight) {
            lightParametersRepository.switchOnLight(idLight);
    }
}
