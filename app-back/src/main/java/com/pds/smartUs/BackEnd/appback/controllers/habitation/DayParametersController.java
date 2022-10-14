package com.pds.smartUs.BackEnd.appback.controllers.habitation;

import com.pds.smartUs.BackEnd.appback.entities.habitation.DayParameters;
import com.pds.smartUs.BackEnd.appback.services.habitation.DayParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path ="/dayParameters")
public class DayParametersController {

    @Autowired
    private DayParametersService dayParametersService;

    @GetMapping("/getDayParameters")
    public Iterable<DayParameters> getDayParameters() {
        return dayParametersService.getDayParameters();
    }
}
