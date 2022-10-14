package com.pds.smartUs.BackEnd.appback.services.habitation;

import com.pds.smartUs.BackEnd.appback.entities.Bepos;
import com.pds.smartUs.BackEnd.appback.entities.Producteur;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.BeposRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BeposService {
    private final BeposRepository beposRepository;

    public Bepos getBeposByhabitationId (Long habitationId){
        return beposRepository.findByHabitationsIdHabitation(habitationId);
    }
}
