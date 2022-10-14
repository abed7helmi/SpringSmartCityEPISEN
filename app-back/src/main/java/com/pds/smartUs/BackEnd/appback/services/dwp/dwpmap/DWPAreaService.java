package com.pds.smartUs.BackEnd.appback.services.dwp.dwpmap;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.DWP_Area;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmap.DWPAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DWPAreaService {

    public final DWPAreaRepository dwpAreaRepository;

    @Autowired
    public DWPAreaService(DWPAreaRepository dwpAreaRepository) {
        this.dwpAreaRepository = dwpAreaRepository;
    }

    public List<DWP_Area> getAreasByArchitecture(int architecture) {
        return dwpAreaRepository.getAreasByArchitecture(architecture);
    }
}
