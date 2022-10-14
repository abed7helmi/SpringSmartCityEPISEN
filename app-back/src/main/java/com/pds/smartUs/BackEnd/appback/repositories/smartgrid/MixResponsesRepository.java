package com.pds.smartUs.BackEnd.appback.repositories.smartgrid;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.MixResponses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MixResponsesRepository extends JpaRepository<MixResponses,Integer> {

    @Query(value = "SELECT * FROM mix_responses WHERE mix_response_id = (SELECT max(mix_response_id) FROM mix_responses)", nativeQuery = true)
    MixResponses getLastMixResponse();

    @Query(value = "select * from mix_responses order by mix_response_id desc limit 4", nativeQuery = true)
    Iterable<MixResponses> getLastestMixResponses();

}
