package com.pds.smartUs.BackEnd.appback.services.habitation;

import com.pds.smartUs.BackEnd.appback.entities.Inhabitant;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.InhabitantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InhabitantService {
    private final InhabitantRepository inhabitantRepository;

    public InhabitantService(InhabitantRepository inhabitantRepository) {
        this.inhabitantRepository = inhabitantRepository;
    }

    public List<Inhabitant> inhabitantLogin(String mail, String password) {
        System.out.println(mail + " " + password + " -- service");
        return inhabitantRepository.inhabitantLogin(mail, password);}

        public Inhabitant inhabitantLogin2(String mail, String password) {
        System.out.println(mail + " " + password + " -- service");
        return inhabitantRepository.inhabitantLogin2(mail, password);}
}
