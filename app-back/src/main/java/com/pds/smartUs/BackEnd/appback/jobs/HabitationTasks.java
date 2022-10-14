package com.pds.smartUs.BackEnd.appback.jobs;

import com.pds.smartUs.BackEnd.appback.entities.Equipment;
import com.pds.smartUs.BackEnd.appback.entities.EquipmentConsomption;
import com.pds.smartUs.BackEnd.appback.entities.Producteur;
import com.pds.smartUs.BackEnd.appback.entities.Production;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.*;
import com.sun.jdi.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class HabitationTasks {

    public static boolean startmock=false;
    //conso
//    public static double coefconso=1.0;
    public static double coefconsoAmpoule=1.0;
    public static double coefconsoClimatiseur=1.0;
    public static double coefconsoTelevision=1.0;
    public static double coefconsoRadiateur=1.0;
    public static double coefconsoCongelateur=1.0;
    public static double coefconsoFour=1.0;
    public static double coefconsoLavelinge=1.0;
    //prod
    public static double coefprod1=1.0;
    public static double coefprod2=1.0;

    @Autowired
    EquipmentConsomptionRepository equipmentConsomptionRepository;

    @Autowired
    DeviceRepository deviceRepository;


    @Autowired
    private ProducteurRepository producteurRepository;

    @Autowired
    ProductionRepository productionRepository;

    @Autowired
    HouseRoomRepository houseRoomRepository;

    @Autowired
    HabitationRepository habitationRepository;

    @Scheduled(fixedRate = 15000L)
    public void productionJob() throws InternalException{

        if (startmock){

            List<Producteur> listprods=producteurRepository.findAll();

            for (Producteur p : listprods) {

                double random1 = (4.7 + Math.random() * (0.2 - 0.1))*coefprod1;
                double random2 = (5.3 + Math.random() * (0.2 - 0.1))*coefprod2;


                LocalDateTime myDateObj = LocalDateTime.now();

                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                String formattedDate = myDateObj.format(myFormatObj);


                try {
                    LocalDateTime date = LocalDateTime.now();


                    Date date1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(formattedDate);
                    if(p.getType().equals("Panneaux Photovoltaliques")){
                        productionRepository.save(new Production(null, date1, random1, p));
                    }else{
                        productionRepository.save(new Production(null, date1, random2, p));
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }



            }
        }


    }

    @Scheduled(fixedRate = 15000L)
    public void consumptionJob() throws InternalException{

        if (startmock){
            List<Equipment> equipmentList = deviceRepository.findAll();

            for (Equipment ep : equipmentList) {
                double randomx =0.0;

                if (ep.getType().equals("Ampoule")){
                    randomx =(0.06 + Math.random() * (0.003 - 0.001))*coefconsoAmpoule;
                }else if(ep.getType().equals("Climatiseur")){
                    randomx =(2.6 + Math.random() * (0.3 - 0.1))*coefconsoClimatiseur;
                }else if(ep.getType().equals("Television")){
                    randomx =(0.1 + Math.random() * (0.03 - 0.01))*coefconsoTelevision;
                }else if(ep.getType().equals("Radiateur")){
                    randomx =(1.2 + Math.random() * (0.03 - 0.01))*coefconsoRadiateur;
                }else if(ep.getType().equals("Congelateur")){
                    randomx =(0.15 + Math.random() * (0.03 - 0.01))*coefconsoCongelateur;
                }else if(ep.getType().equals("Lave-linge")){
                    randomx =(2.2 + Math.random() * (0.5 - 0.1))*coefconsoLavelinge;
                }else if(ep.getType().equals("Four")){
                    randomx =(2.0 + Math.random() * (0.5 - 0.1))*coefconsoFour;
                }





                LocalDateTime myDateObj = LocalDateTime.now();

                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                String formattedDate = myDateObj.format(myFormatObj);


                try {
                    Date date1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(formattedDate);

                    equipmentConsomptionRepository.save(new EquipmentConsomption(null, date1, randomx, ep));

                } catch (ParseException e) {
                    e.printStackTrace();
                }



            }
        }





    }



}
