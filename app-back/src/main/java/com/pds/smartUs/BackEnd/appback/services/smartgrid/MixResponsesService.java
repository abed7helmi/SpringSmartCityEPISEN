package com.pds.smartUs.BackEnd.appback.services.smartgrid;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.MixResponses;
import com.pds.smartUs.BackEnd.appback.repositories.smartgrid.MixResponsesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MixResponsesService {

    @Autowired
    private MixResponsesRepository mixResponsesRepository;

    public List<MixResponses> getAllMixResponse(){
        return mixResponsesRepository.findAll();
    }

    public void saveMixResponse(MixResponses mixResponses){
        mixResponsesRepository.save(mixResponses);
    }

    public MixResponses getLastMixResponse(){
        return mixResponsesRepository.getLastMixResponse();
    }

    public Iterable<MixResponses> getLastestMixResponses(){
        return mixResponsesRepository.getLastestMixResponses();
    }
}
