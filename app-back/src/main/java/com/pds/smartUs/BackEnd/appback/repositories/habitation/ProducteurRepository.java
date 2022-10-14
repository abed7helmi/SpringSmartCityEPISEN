package com.pds.smartUs.BackEnd.appback.repositories.habitation;

import com.pds.smartUs.BackEnd.appback.entities.Producteur;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface ProducteurRepository extends JpaRepository<Producteur,Long> {


    public List<Producteur> findAllByBeposId (Long beposid);


}
