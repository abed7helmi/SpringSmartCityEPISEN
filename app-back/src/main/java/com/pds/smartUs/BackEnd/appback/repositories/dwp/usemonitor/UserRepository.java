package com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "select 1 from app_user where user_name = :name", nativeQuery=true)
    Integer checkName(@Param("name") String name);

}
