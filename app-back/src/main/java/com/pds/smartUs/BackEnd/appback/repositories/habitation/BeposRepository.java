package com.pds.smartUs.BackEnd.appback.repositories.habitation;

import com.pds.smartUs.BackEnd.appback.entities.Bepos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BeposRepository extends JpaRepository<Bepos,Long> {
    public Bepos findByHabitationsIdHabitation (Long idhabitation);

}
