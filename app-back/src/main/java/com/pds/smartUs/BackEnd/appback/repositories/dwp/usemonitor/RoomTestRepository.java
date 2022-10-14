package com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.RoomTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTestRepository extends CrudRepository<RoomTest, Integer> {

}
