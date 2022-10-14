package com.pds.smartUs.BackEnd.appback.controllers.habitation;

import com.pds.smartUs.BackEnd.appback.entities.Inhabitant;
import com.pds.smartUs.BackEnd.appback.services.habitation.InhabitantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/login")
public class InhabitantController {

    @Autowired
    private InhabitantService inhabitantService;


    @GetMapping("/inhabitantcnx/{mail}/{password}")
    //public List<Inhabitant> inhabitantLogin(@PathVariable("mail") String mail, @PathVariable("password") String password) {
    public Inhabitant inhabitantLogin2(@PathVariable String mail, @PathVariable String password) {
        System.out.println(" -- controller");
        return inhabitantService.inhabitantLogin2(mail, password);
    }


    @GetMapping("/inhabitant")
    //public List<Inhabitant> inhabitantLogin(@PathVariable("mail") String mail, @PathVariable("password") String password) {
    public List<Inhabitant> inhabitantLogin(@RequestParam String mail, @RequestParam String password) {
        System.out.println(" -- controller");
        return inhabitantService.inhabitantLogin(mail, password);
    }
}
