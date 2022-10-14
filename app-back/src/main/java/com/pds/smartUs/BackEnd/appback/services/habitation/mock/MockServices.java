package com.pds.smartUs.BackEnd.appback.services.habitation.mock;

import com.pds.smartUs.BackEnd.appback.entities.habitation.DayParameters;
import com.pds.smartUs.BackEnd.appback.services.habitation.DayParametersService;
import com.pds.smartUs.BackEnd.appback.services.habitation.LightParametersService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class MockServices {

    LightParametersService lightParametersService;

    public MockServices() {

    }

    public void dayParametersMock(int scenario) throws InterruptedException {
        System.out.println("etape 2");

        //if ?scenario = 1 we will mock the first scenario
        if(scenario == 1) {
            LocalDate date = LocalDate.of(2022, 06, 24);
            LocalTime time = LocalTime.of(15, 30, 00);
            double temp = 15;

            while (true) {
                System.out.println("etape 3");

                if ((time.getHour() == 23) && (time.getMinute() >= 50)) {
                    date = date.plusDays(1);
                    System.out.println(date);
                }

                time = time.plusMinutes(10);


                if ((time.getHour() > 7) && (time.getHour() < 16)) {
                    temp = (float) (temp + 0.2);
                } else {
                    temp = (float) (temp - 0.1);
                }

                DayParameters dayParametersToInsert = new DayParameters(date, time, temp);
                DayParametersService.addDayParameters(dayParametersToInsert);

                LightParametersService.lightsControl(1, dayParametersToInsert);

                Thread.sleep(500);
            }
        }

        //TO DO : scénario hiver
        /*
        if(scenario == 2) {
            LocalDate date = LocalDate.of(2022, 06, 24);
            LocalTime time = LocalTime.of(15, 30, 00);
            double temp = 7;

            while (true) {
                System.out.println("etape 3");

                if ((time.getHour() == 23) && (time.getMinute() >= 50)) {
                    date = date.plusDays(1);
                    System.out.println(date);
                }

                time = time.plusMinutes(10);


                if ((time.getHour() > 7) && (time.getHour() < 16)) {
                    temp = (float) (temp + 0.2);
                } else {
                    temp = (float) (temp - 0.1);
                }

                DayParameters dayParametersToInsert = new DayParameters(date, time, temp);
                DayParametersService.addDayParameters(dayParametersToInsert);

                LightParametersService.lightsControl(1, dayParametersToInsert);

                Thread.sleep(2500);
            }


        }*/

    }
}
