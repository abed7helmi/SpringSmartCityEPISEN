package com.pds.smartUs.BackEnd.appback.repositories.habitation;


import com.pds.smartUs.BackEnd.appback.entities.Habitation;
import com.pds.smartUs.BackEnd.appback.entities.Inhabitant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HabitationRepository extends JpaRepository<Habitation,Long> {
    @Query(value = "SELECT * from inhabitant2 where id_inhabitant = ?1 ; ", nativeQuery = true)
    public Habitation findByIdInhabitant(Long userid);

    @Query(value = "select habitation_id from inhabitant2 where id_inhabitant= ?1 ; ", nativeQuery = true)
//    @Query(value = "select habitation_id from inhabitant2 where id_inhabitant= ?1 ; ", nativeQuery = true)
    public Long findByIdInhabitant2(Long userid);
}
