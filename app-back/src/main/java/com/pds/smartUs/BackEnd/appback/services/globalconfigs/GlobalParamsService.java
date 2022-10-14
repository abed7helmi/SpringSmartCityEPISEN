package com.pds.smartUs.BackEnd.appback.services.globalconfigs;

import com.pds.smartUs.BackEnd.appback.entities.globalconfigs.GlobalParam;
import com.pds.smartUs.BackEnd.appback.gloabalconfig.GlobalVariablesEnum;
import com.pds.smartUs.BackEnd.appback.repositories.globalconfigs.GlobalParamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlobalParamsService {

    @Autowired
    private GlobalParamsRepository globalParamsRepository;

    public List<GlobalParam> getGlobalParams() {
        return globalParamsRepository.findAll();
    }

    public String getParamValue(GlobalVariablesEnum param){
        return globalParamsRepository.getGlobalParamByParamName(param.name()).getParamValue();
    }

    public void updateGlobalParam(int id, String value) {
        GlobalParam param = globalParamsRepository.getById(id);
        param.setParamValue(value);
        globalParamsRepository.save(param);
    }
}
