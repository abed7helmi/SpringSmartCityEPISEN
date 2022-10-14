package com.pds.smartUs.BackEnd.appback.repositories.habitation;


import com.pds.smartUs.BackEnd.appback.entities.Inhabitant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InhabitantRepository extends CrudRepository<Inhabitant, Long> {

    @Query(value = "SELECT * from inhabitant where inhabitant_email = :mail and inhabitant_password = :password ; ", nativeQuery = true)
    public List<Inhabitant> inhabitantLogin(@Param("mail") String mail, @Param("password") String password);

    @Query(value = "SELECT * from inhabitant2 where inhabitant_email = :mail and inhabitant_password = :password ; ", nativeQuery = true)
    public Inhabitant inhabitantLogin2(@Param("mail") String mail, @Param("password") String password);


}
