package com.pds.smartUs.BackEnd.appback.services.globalconfigs;

import com.pds.smartUs.BackEnd.appback.entities.globalconfigs.FeatureFlagEntity;
import com.pds.smartUs.BackEnd.appback.gloabalconfig.FeatureFlagsEnum;
import com.pds.smartUs.BackEnd.appback.repositories.globalconfigs.FeatureFlagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureFlagService {

    @Autowired
    private FeatureFlagsRepository featureFlagsRepository;

    public FeatureFlagService() {
    }

    public boolean getFlagStatusByName(FeatureFlagsEnum featureFlag) {
        return featureFlagsRepository.getFeatureFlagEntityByFlagName(featureFlag.name()).getFlagValue();
    }

    public List<FeatureFlagEntity> getFeatureFlags() {
        return featureFlagsRepository.findAll();
    }

    public void updateFeatureFlag(int id, boolean value) {
        FeatureFlagEntity flag = featureFlagsRepository.getById(id);
        flag.setFlagValue(value);
        featureFlagsRepository.save(flag);
    }
}
