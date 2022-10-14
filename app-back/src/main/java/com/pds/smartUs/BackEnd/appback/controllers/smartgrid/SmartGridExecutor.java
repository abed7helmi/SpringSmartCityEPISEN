package com.pds.smartUs.BackEnd.appback.controllers.smartgrid;

import javax.annotation.PostConstruct;

import com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor.algoconso.AlgoConsoDevice;
import com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor.algoconso.AlgoConsoHeating;
import com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor.algoconso.AlgoConsoRoom;
import com.pds.smartUs.BackEnd.appback.simulator.workers.SmartGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class SmartGridExecutor {

    private static final Logger logger = LoggerFactory.getLogger(SmartGridExecutor.class);

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private ApplicationContext applicationContext;


    @PostConstruct
    public void atStartup() {
        SmartGrid simulator = applicationContext.getBean(SmartGrid.class);
        taskExecutor.execute(simulator);
        logger.warn("SmartGrid Executor Startup");

        /*AlgoConsoDevice algoConsoDevice = applicationContext.getBean(AlgoConsoDevice.class);
        taskExecutor.execute(algoConsoDevice);

        AlgoConsoHeating algoConsoHeating = applicationContext.getBean(AlgoConsoHeating.class);
        taskExecutor.execute(algoConsoHeating);*/

        AlgoConsoRoom algoConsoRoom = applicationContext.getBean(AlgoConsoRoom.class);
        taskExecutor.execute(algoConsoRoom);
    }
}

