package com.pds.smartUs.BackEnd.appback.repositories.globalconfigs;

import com.pds.smartUs.BackEnd.appback.entities.globalconfigs.GlobalParam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlobalParamsRepository extends JpaRepository<GlobalParam, Integer> {
    public GlobalParam getGlobalParamByParamName(String paraName);
}
