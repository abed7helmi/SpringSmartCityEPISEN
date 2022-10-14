package com.pds.smartUs.BackEnd.appback.repositories.globalconfigs;

import com.pds.smartUs.BackEnd.appback.entities.globalconfigs.FeatureFlagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureFlagsRepository extends JpaRepository<FeatureFlagEntity, Integer> {

    FeatureFlagEntity getFeatureFlagEntityByFlagName(String flagName);
}
