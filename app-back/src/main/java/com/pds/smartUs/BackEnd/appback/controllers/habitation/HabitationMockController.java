package com.pds.smartUs.BackEnd.appback.controllers.habitation;

import com.pds.smartUs.BackEnd.appback.jobs.HabitationTasks;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HabitationMockController {

    private HabitationTasks habitationTasks;

//    general mock
    @GetMapping("/startmock")
    public void startmock() {
        habitationTasks.startmock=true;

    }

    @GetMapping("/stopmock")
    public void stopmock() {
        habitationTasks.startmock=false;
    }

    //conso

    @GetMapping("/startmockconso")
    public void startmockconso() {
        habitationTasks.coefconsoAmpoule=1;
        habitationTasks.coefconsoClimatiseur=1;
        habitationTasks.coefconsoTelevision=1;
        habitationTasks.coefconsoRadiateur=1;
        habitationTasks.coefconsoCongelateur=1;
        habitationTasks.coefconsoLavelinge=1;
        habitationTasks.coefconsoFour=1;
    }

    @GetMapping("/stopmockconso")
    public void stopmockconso() {
        habitationTasks.coefconsoAmpoule=0;
        habitationTasks.coefconsoClimatiseur=0;
        habitationTasks.coefconsoTelevision=0;
        habitationTasks.coefconsoRadiateur=0;
        habitationTasks.coefconsoCongelateur=0;
        habitationTasks.coefconsoLavelinge=0;
        habitationTasks.coefconsoFour=0;
    }

    @GetMapping("/stopmockconsoFour")
    public void stopmockconsoFour() {

        habitationTasks.coefconsoFour=0;
    }

    @GetMapping("/startmockconsoFour")
    public void startmockconsoFour() {

        habitationTasks.coefconsoFour=1;
    }

    @GetMapping("/stopmockconsoLavelinge")
    public void stopmockconsoLavelinge() {
        habitationTasks.coefconsoLavelinge=0;
    }

    @GetMapping("/startmockconsoLavelinge")
    public void startmockconsoLavelinge() {
        habitationTasks.coefconsoLavelinge=1;
    }

    @GetMapping("/stopmockconsoCongelateur")
    public void stopmockconsoCongelateur() {
        habitationTasks.coefconsoCongelateur=0;

    }

    @GetMapping("/startmockconsoCongelateur")
    public void startmockconsoCongelateur() {
        habitationTasks.coefconsoCongelateur=1;

    }


    @GetMapping("/stopmockconsoRadiateur")
    public void stopmockconsoRadiateur() {

        habitationTasks.coefconsoRadiateur=0;

    }

    @GetMapping("/startmockconsoRadiateur")
    public void startmockconsoRadiateur() {

        habitationTasks.coefconsoRadiateur=1;

    }

    @GetMapping("/stopmockconsoTelevision")
    public void stopmockconsoTelevision() {

        habitationTasks.coefconsoTelevision=0;

    }

    @GetMapping("/startmockconsoTelevision")
    public void startmockconsoTelevision() {

        habitationTasks.coefconsoTelevision=1;

    }

    @GetMapping("/stopmockconsoClimatiseur")
    public void stopmockconsoClimatiseur() {
        habitationTasks.coefconsoClimatiseur=0;
    }

    @GetMapping("/startmockconsoClimatiseur")
    public void startmockconsoClimatiseur() {
        habitationTasks.coefconsoClimatiseur=1;
    }

    @GetMapping("/stopmockconsoAmpoule")
    public void stopmockconsoAmpoule() {
        habitationTasks.coefconsoAmpoule=0;

    }
    @GetMapping("/startmockconsoAmpoule")
    public void startmockconsoAmpoule() {
        habitationTasks.coefconsoAmpoule=1;

    }


    @GetMapping("/mockjourhiver")
    public void mockjourhiver() {
        habitationTasks.coefconsoAmpoule=0.1;
        habitationTasks.coefconsoClimatiseur=0;
        habitationTasks.coefconsoTelevision=0.9;
        habitationTasks.coefconsoRadiateur=0.9;
        habitationTasks.coefconsoCongelateur=0.9;
        habitationTasks.coefconsoLavelinge=0;
        habitationTasks.coefconsoFour=0;

    }

    @GetMapping("/mockjourete")
    public void mockjourete() {
        habitationTasks.coefconsoAmpoule=0.95;
        habitationTasks.coefconsoClimatiseur=1;
        habitationTasks.coefconsoTelevision=0.9;
        habitationTasks.coefconsoRadiateur=0;
        habitationTasks.coefconsoCongelateur=1;
        habitationTasks.coefconsoLavelinge=0;
        habitationTasks.coefconsoFour=0;

    }



    //prod

    @GetMapping("/stopmockprod")
    public void stopmockprod() {
        habitationTasks.coefprod1=0;
        habitationTasks.coefprod2=0;
    }

    @GetMapping("/startmockprod")
    public void startmockprod() {
        habitationTasks.coefprod1=1;
        habitationTasks.coefprod2=1;
    }

    @GetMapping("/stopmockprod1")
    public void stopmockprod1() {
        habitationTasks.coefprod1=1;
        habitationTasks.coefprod2=0;
    }

    @GetMapping("/stopmockprod2")
    public void stopmockprod2() {
        habitationTasks.coefprod1=0;
        habitationTasks.coefprod2=1;
    }

    @GetMapping("/mockprodhiverjournee")
    public void mockprodhiverjour() {
        habitationTasks.coefprod1=0.4;
        habitationTasks.coefprod2=0.85;
    }

    @GetMapping("/mockprodhivernuit")
    public void mockprodhivernuit() {
        habitationTasks.coefprod1=0.18;
        habitationTasks.coefprod2=0.7;
    }

    @GetMapping("/mockprodetejournee")
    public void mockprodetejournee() {
        habitationTasks.coefprod1=0.95;
        habitationTasks.coefprod2=0.35;
    }

    @GetMapping("/mockprodetenuit")
    public void mockprodetenuit() {
        habitationTasks.coefprod1=0.3;
        habitationTasks.coefprod2=0.2;
    }



}
