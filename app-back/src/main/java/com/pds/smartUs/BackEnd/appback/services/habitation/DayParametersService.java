package com.pds.smartUs.BackEnd.appback.services.habitation;

import com.pds.smartUs.BackEnd.appback.entities.habitation.DayParameters;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.DayParametersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayParametersService {
    @Autowired
    private static DayParametersRepository dayParametersRepository;

    public DayParametersService(DayParametersRepository dayParametersRepository) {
        this.dayParametersRepository = dayParametersRepository;
    }


    public static void addDayParameters(DayParameters dayParameters) {
        System.out.print(dayParameters);
        dayParametersRepository.save(dayParameters);
    }

    public List<DayParameters> getDayParameters() {
        return (List<DayParameters>) dayParametersRepository.findAll();
    }


    public DayParametersRepository getDayParametersRepository() {
        return dayParametersRepository;
    }
}
