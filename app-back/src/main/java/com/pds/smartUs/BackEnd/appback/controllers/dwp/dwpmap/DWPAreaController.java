package com.pds.smartUs.BackEnd.appback.controllers.dwp.dwpmap;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.DWP_Area;
import com.pds.smartUs.BackEnd.appback.services.dwp.dwpmap.DWPAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DWPAreaController {

    private final DWPAreaService dwpAreaService;

    @Autowired
    public DWPAreaController(DWPAreaService dwpAreaService) {
        this.dwpAreaService = dwpAreaService;
    }

    @GetMapping("/dwp/areas/{architecture}")
    public List<DWP_Area> getAreasByArchitecture(@PathVariable("architecture") int architecture){
        return dwpAreaService.getAreasByArchitecture(architecture);
    }
}
