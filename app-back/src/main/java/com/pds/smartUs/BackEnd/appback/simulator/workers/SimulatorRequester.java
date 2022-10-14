package com.pds.smartUs.BackEnd.appback.simulator.workers;

import com.pds.smartUs.BackEnd.appback.gloabalconfig.GlobalVariablesEnum;
import com.pds.smartUs.BackEnd.appback.services.globalconfigs.GlobalParamsService;
import com.pds.smartUs.BackEnd.appback.simulator.config.SmartGridVariables;
import com.pds.smartUs.BackEnd.appback.simulator.entities.SimulatorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.io.IOError;
import java.io.IOException;
import java.net.ConnectException;

@Service
public class SimulatorRequester implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(SimulatorRequester.class);

    private final GlobalParamsService globalParamsService;
    private SimulatorResponse simulatorResponse;

    private String simulatorHost = "";
    private String simulatorPort = "";

    public SimulatorRequester(GlobalParamsService globalParamsService) {
        this.globalParamsService = globalParamsService;
        this.simulatorHost = globalParamsService.getParamValue(GlobalVariablesEnum.SIMULATOR_BACK_HOST);
        this.simulatorPort = globalParamsService.getParamValue(GlobalVariablesEnum.SIMULATOR_BACK_PORT);
    }

    @Override
    public void run() {
        boolean isSimulatorUp = true;
        while (true) {
            try {
                simulatorResponse = makeRequest();
                if(!isSimulatorUp) isSimulatorUp = true;
                Thread.sleep(SmartGridVariables.SMART_GRID_SIMULATOR_REFRESH);
            }
            catch (InterruptedException e) {
                //logger.warn("SimulatorRequester Thread has been TERMINATED");
                break;
            }
            catch (ResourceAccessException e) {
                if(isSimulatorUp) {
                    //logger.error("Simulator is unreachable at :"+simulatorHost);
                    isSimulatorUp = false;
                }
            }
        }
    }

    public SimulatorResponse getSimulatorResponse() {
        if(simulatorResponse == null) {
            try {
                return makeRequest();
            } catch (ResourceAccessException e) {
                //logger.error("Simulator is unreachable at : "+simulatorHost);
            }
        }
        return simulatorResponse;
    }

    public SimulatorResponse makeRequest() {
        return new RestTemplate().getForObject(
                "http://"+globalParamsService.getParamValue(GlobalVariablesEnum.SIMULATOR_BACK_HOST)
                        +":"+globalParamsService.getParamValue(GlobalVariablesEnum.SIMULATOR_BACK_PORT)
                        +"/v2/simulator/params",
                SimulatorResponse.class);
    }
}
