package com.pds.smartUs.BackEnd.appback.repositories.smartgrid.mixalgos;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.mixalgos.EnergyMixAlgos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EnergyMixAlgosRepository extends JpaRepository<EnergyMixAlgos, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update energy_mix_algos set economic_algo_state = true, default_algo_state = false, percentage_algo_state = false, environmental_algo_state = false where id=1", nativeQuery = true)
    void activateEconomicAlgo();

    @Modifying
    @Transactional
    @Query(value = "update energy_mix_algos set economic_algo_state = false, default_algo_state = true, percentage_algo_state = false, environmental_algo_state = false where id=1", nativeQuery = true)
    void activateDefaultAlgo();

    @Modifying
    @Transactional
    @Query(value = "update energy_mix_algos set economic_algo_state = false, default_algo_state = false, percentage_algo_state = true, environmental_algo_state = false where id=1", nativeQuery = true)
    void activatePercentageAlgo();

    @Modifying
    @Transactional
    @Query(value = "update energy_mix_algos set economic_algo_state = false, default_algo_state = false, percentage_algo_state = false, environmental_algo_state = true where id=1", nativeQuery = true)
    void activateEnvironmentalAlgo();

}
