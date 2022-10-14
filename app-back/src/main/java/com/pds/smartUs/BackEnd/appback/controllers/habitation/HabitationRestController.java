package com.pds.smartUs.BackEnd.appback.controllers.habitation;


import com.pds.smartUs.BackEnd.appback.entities.Habitation;
import com.pds.smartUs.BackEnd.appback.entities.Inhabitant;
import com.pds.smartUs.BackEnd.appback.services.habitation.HabitationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class HabitationRestController {
    private final HabitationService habitationService;



    @GetMapping("/home/{idHabitation}")
    public Habitation getHabitation(@PathVariable Long idHabitation){


        return habitationService.gethabitation(idHabitation);
    }


    @GetMapping("/resumhabitation/{idHabitation}")
    public ResponseEntity<?> getResumHabitation (@PathVariable Long idHabitation){
        return ResponseEntity.ok(habitationService.getHabitationResumByHabitationId(idHabitation));

    }

    @GetMapping("/comparisonhabitation/{habitationId}")
    public ResponseEntity<?> getComparisonHabitation (@PathVariable Long habitationId){
        return ResponseEntity.ok(habitationService.getComparisonByHabitationId(habitationId));

    }

    @GetMapping("/infouser/{iduser}")
    public Inhabitant getInhabitant(@PathVariable Long iduser){

        return habitationService.getInhabitant(iduser);

    }

    @GetMapping("/infohabitation/{iduser}")
    public Habitation getHabitationByInhabitantId(@PathVariable Long iduser){

        return habitationService.getHabitationByInhabitantId(iduser);

    }

    @GetMapping("/habitationid/{iduser}")
    public Long  getidHabitationByInhabitantId(@PathVariable Long iduser){

        return habitationService.getHabitationByInhabitantId2(iduser);

    }

}
