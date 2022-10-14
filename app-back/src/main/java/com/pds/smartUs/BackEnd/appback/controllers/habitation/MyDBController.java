package com.pds.smartUs.BackEnd.appback.controllers.habitation;

import com.pds.smartUs.BackEnd.appback.entities.*;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyDBController {

    @Autowired
    EquipmentConsomptionRepository equipmentConsomptionRepository;

    @Autowired
    DeviceRepository deviceRepository;


    @Autowired
    private ProducteurRepository producteurRepository;


    @Autowired
    HouseRoomRepository houseRoomRepository;

    @Autowired
    HabitationRepository habitationRepository;

    @Autowired
    InhabitantRepository inhabitantRepository;

    @Autowired
    BeposRepository beposRepository;

    @GetMapping(path = "/adddbhabitation")
    public void adddbhabitation (){

//        beposRepository.save(new Bepos(null,"B4","Saint Maur des fossés",null,null,null));
        Bepos bp1 =beposRepository.findById(1L).get();

        producteurRepository.save(new Producteur(null, "Systovi", "V-SYS Pro", "Panneaux Photovoltaliques", "4700 Wc", "Actif", bp1,null));
        producteurRepository.save(new Producteur(null, "Gual", "Gual StatoEolien GSE 3", "Éolienne domestique ", "5400 W", "Actif", bp1,null));

        habitationRepository.save(new Habitation(null,94100,"avenue de lattre de tassigny, Saint Maur des fossés","5","perso",true,null,bp1,null));

        Habitation h1 = habitationRepository.findById(1L).get();

        inhabitantRepository.save(new Inhabitant(null,"ABED","Helmi","ab.helmi@yahoo.fr","dsdsdsz",h1));

        houseRoomRepository.save(new HouseRoom(null,"Chambre",30,4,null,h1));
        houseRoomRepository.save(new HouseRoom(null,"Cuisine",20,4,null,h1));
        houseRoomRepository.save(new HouseRoom(null,"Toilette",7,1,null,h1));

        HouseRoom hr1 =houseRoomRepository.findById(1L).get();
        HouseRoom hr2 =houseRoomRepository.findById(2L).get();
        HouseRoom hr3 =houseRoomRepository.findById(3L).get();


        deviceRepository.save(new Equipment(null, "Ampoule", "Tapo A7",  "60 w", null, hr1));
        deviceRepository.save(new Equipment(null, "Climatiseur", "SIMENS 888",  "2600 w", null, hr1));
        deviceRepository.save(new Equipment(null, "Television", "Sony smart LCD 8",  "100 w", null, hr1));
        deviceRepository.save(new Equipment(null, "Radiateur", "Windsor",  "1200 w", null, hr1));


        deviceRepository.save(new Equipment(null, "Ampoule", "Tapo A7",  "60 w", null, hr2));
        deviceRepository.save(new Equipment(null, "Congelateur", "LG 028",  "150 w", null, hr2));
        deviceRepository.save(new Equipment(null, "Four", "BOSH T21",  "2000 w", null, hr2));


        deviceRepository.save(new Equipment(null, "Ampoule", "Tapo A7",  "60 w", null, hr3));
        deviceRepository.save(new Equipment(null, "Lave-linge", "BEKO 98A",  "2200 w", null, hr3));





    }


    @GetMapping(path = "/adddbhabitation2")
    public void adddbhabitation2 (){


        Bepos bp1 =beposRepository.findById(1L).get();

//        producteurRepository.save(new Producteur(null, "Systovi", "V-SYS Pro", "Panneaux Photovoltaliques", "3300 Wc", "Actif", bp1,null));
//        producteurRepository.save(new Producteur(null, "Gual", "Gual StatoEolien GSE 3", "Éolienne domestique ", "2700 W", "Actif", bp1,null));

        habitationRepository.save(new Habitation(2L,94100,"avenue foch, Saint Maur des fossés","17","perso",true,null,bp1,null));

        Habitation h1 = habitationRepository.findById(2L).get();

        inhabitantRepository.save(new Inhabitant(2L,"Bouali","Yohan","yohan.bouali@yahoo.fr","wiiiw",h1));

        houseRoomRepository.save(new HouseRoom(4L,"Chambre parentale",30,4,null,h1));
        houseRoomRepository.save(new HouseRoom(5L,"Chambre thomas",23,3,null,h1));
        houseRoomRepository.save(new HouseRoom(6L,"Cuisine",21,4,null,h1));
        houseRoomRepository.save(new HouseRoom(7L,"Toilette",9,1,null,h1));

        HouseRoom hr1 =houseRoomRepository.findById(4L).get();
        HouseRoom hr2 =houseRoomRepository.findById(5L).get();
        HouseRoom hr3 =houseRoomRepository.findById(6L).get();
        HouseRoom hr4 =houseRoomRepository.findById(7L).get();


        deviceRepository.save(new Equipment(null, "Ampoule", "HUAWEI H7",  "60 w", null, hr1));
        deviceRepository.save(new Equipment(null, "Climatiseur", "SIMENS 888",  "2600 w", null, hr1));
        deviceRepository.save(new Equipment(null, "Television", "Sony smart LCD 8",  "100 w", null, hr1));
        deviceRepository.save(new Equipment(null, "Radiateur", "Windsor",  "1200 w", null, hr1));

        deviceRepository.save(new Equipment(null, "Ampoule", "HUAWEI H7",  "60 w", null, hr2));
        deviceRepository.save(new Equipment(null, "Climatiseur", "SIMENS 888",  "2600 w", null, hr2));
        deviceRepository.save(new Equipment(null, "Television", "Sony smart LCD 8",  "100 w", null, hr2));
        deviceRepository.save(new Equipment(null, "Radiateur", "Windsor",  "1200 w", null, hr2));


        deviceRepository.save(new Equipment(null, "Ampoule", "HUAWEI H7",  "60 w", null, hr3));
        deviceRepository.save(new Equipment(null, "Congelateur", "LG 028",  "150 w", null, hr3));
        deviceRepository.save(new Equipment(null, "Four", "BOSH T21",  "2000 w", null, hr3));


        deviceRepository.save(new Equipment(null, "Ampoule", "HUAWEI H7",  "60 w", null, hr4));
        deviceRepository.save(new Equipment(null, "Lave-linge", "BEKO 98A",  "2200 w", null, hr4));





    }


    @GetMapping(path = "/adddbhabitation3")
    public void adddbhabitation3 (){

        //beposRepository.save(new Bepos(null,"B3","Champigny-sur-Marne",null,null,null));
        // Bepos bp1 =beposRepository.findById(2L).get();


        //producteurRepository.save(new Producteur(null, "Imex", "IMEX-Eol GP", "Éolienne domestique ", "2700 W", "Actif", bp1,null));
//
        //habitationRepository.save(new Habitation(null,94100,"Avenue Gabriel Peri, Champigny-sur-Marne","19","perso",true,null,bp1,null));

        Habitation h1 = habitationRepository.findById(3L).get();

        //inhabitantRepository.save(new Inhabitant(null,"Benfrid","Kinan","kinan.benfrid@yahoo.fr","wiiiiiw",h1));
//
        //houseRoomRepository.save(new HouseRoom(null,"Chambre",20,2,null,h1));
        //houseRoomRepository.save(new HouseRoom(null,"Cuisine",10,1,null,h1));
        //houseRoomRepository.save(new HouseRoom(null,"Salle de bain",8,1,null,h1));

        HouseRoom hr1 =houseRoomRepository.findById(8L).get();
        HouseRoom hr2 =houseRoomRepository.findById(9L).get();
        HouseRoom hr3 =houseRoomRepository.findById(10L).get();
//        HouseRoom hr4 =houseRoomRepository.findById(11L).get();
//
//
        //deviceRepository.save(new Equipment(null, "Ampoule", "IKEA I11",  "60 w", null, hr1));
        //deviceRepository.save(new Equipment(null, "Television", "xiaomi smart LCD 8",  "100 w", null, hr1));
        //deviceRepository.save(new Equipment(null, "Radiateur", "Windsor",  "1200 w", null, hr1));




        //deviceRepository.save(new Equipment(null, "Ampoule", "IKEA I11",  "60 w", null, hr2));
        //deviceRepository.save(new Equipment(null, "Congelateur", "LG 028",  "150 w", null, hr2));

//
//      // ca marche pas
        //deviceRepository.save(new Equipment(null, "Ampoule", "IKEA I11",  "60 w", null, hr3));
        //deviceRepository.save(new Equipment(null, "Lave-linge", "BEKO 98A",  "2200 w", null, hr3));
//
//
//
//

    }


}
