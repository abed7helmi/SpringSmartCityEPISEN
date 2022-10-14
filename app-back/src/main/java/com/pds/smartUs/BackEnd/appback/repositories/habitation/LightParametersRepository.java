package com.pds.smartUs.BackEnd.appback.repositories.habitation;

import com.pds.smartUs.BackEnd.appback.entities.habitation.LightParameters;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LightParametersRepository extends CrudRepository<LightParameters, Integer> {



    @Modifying
    @Transactional
    @Query(value="update light_parameters SET is_active = false where id_light_parameters = :idLight ;", nativeQuery = true)
    public void switchOffLight(@Param("idLight") int idLight);

    @Modifying
    @Transactional
    @Query(value="update light_parameters SET is_active = true where id_light_parameters = :idLight ;", nativeQuery = true)
    public void switchOnLight(@Param("idLight") int idLight);

}
