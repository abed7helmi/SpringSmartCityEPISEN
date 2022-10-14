package com.pds.smartUs.BackEnd.appback.services.dwp.dwpmap;


import com.pds.smartUs.BackEnd.appback.entities.dwpmap.DWP;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmap.DWPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DWPService {
    private final DWPRepository dwpRepository;

    @Autowired
    public DWPService(DWPRepository dwpRepository){
        this.dwpRepository=dwpRepository;
    }

    public DWP getDWPById(int id_dwp){
        return dwpRepository.findById(id_dwp).get();
    }


    @Transactional
    public void updateConfigured(boolean value, int id_dwp) {
        DWP dwp= dwpRepository.findById(id_dwp).orElseThrow(() -> new IllegalStateException(
                "dwp with id " + id_dwp + " does not exist"));

        dwp.setConfigured(value);

    }

    public List<DWP> getDWPByIdBuilding(int id_building) {
        return dwpRepository.getDWPByIdBuilding(id_building);
    }
    public List<DWP> getDWPByIdBuilding(int id_building,boolean configured) {
        return dwpRepository.getDWPByIdBuilding(id_building,configured);
    }


}
