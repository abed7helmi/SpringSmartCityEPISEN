package com.pds.smartUs.BackEnd.appback.services.habitation;

import com.pds.smartUs.BackEnd.appback.entities.*;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.HabitationRepository;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.InhabitantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HabitationService {


    //    Deo
    @Autowired
    private final HabitationRepository habitationRepository;
    @Autowired
    private final InhabitantRepository inhabitantRepository;



    //    Services
    @Autowired
    private final HouseRoomService houseRoomService;

    private final EquipmentService equipmentService;

    @Autowired
    private final ProductionService productionService;

    @Autowired
    private final BeposService beposService;


    public Habitation gethabitation(Long habitationid) {

        return habitationRepository.findById(habitationid).get();

    }

    public Inhabitant getInhabitant(Long Inhabitantid) {

        return inhabitantRepository.findById(Inhabitantid).get();
    }


    public Map<LocalDate, Map<String, Double>> getComparisonByHabitationId(Long habitationId) {


        LocalDateTime today = LocalDateTime.now().plusDays(1);
        LocalDate todaytest = today.toLocalDate();


        ArrayList<LocalDate> dates = new ArrayList<LocalDate>();

        for (int i = 0; i < 8; i++) {
            dates.add(todaytest);
            todaytest = todaytest.minusDays(1);

        }
        Bepos bepos = beposService.getBeposByhabitationId(habitationId);
        List<Double> productionhistorique = productionService.getProductionsOfProducersBydate(bepos.getId(), dates);
        List<Double> consumptionhistorique = equipmentService.getConsumptionBydate(habitationId, dates);


        Map<LocalDate, Map<String, Double>> result = new HashMap<>();


        for (int i = 0; i < dates.size() - 1; i++) {
            Map<String, Double> resum = new HashMap<>();
            resum.put("conso", consumptionhistorique.get(i));
            resum.put("prod", productionhistorique.get(i));

            result.put(dates.get(i + 1), resum);
        }


        return result;
    }


    public Map<String, Double> getHabitationResumByHabitationId(Long habitationId) {
        Map<String, Double> houseroomconso = houseRoomService.getRoomEquipmentsConsumptionByHabitationId(habitationId);
        Bepos bepos = beposService.getBeposByhabitationId(habitationId);

        Map<String, List<Production>> beposprod = productionService.getProductionsOfProducers(bepos.getId());

        Double surfacetotalbepos = 0.0;
        Double surfacetotalhabitation = 0.0;
        for (Habitation habitation : bepos.getHabitations()) {

            for (HouseRoom houseRoom : habitation.getHouseRooms()) {
                surfacetotalbepos += houseRoom.getSurface();
                if (habitation.getIdHabitation() == habitationId) {
                    surfacetotalhabitation += houseRoom.getSurface();
                }

            }
        }

        Double consototal = 0.0;
        for (Double f : houseroomconso.values()) {
            consototal += f;
        }

        Double prodtotal = 0.0;
        for (Map.Entry<String, List<Production>> entry : beposprod.entrySet()) {
            prodtotal += entry.getValue().get(0).getProduction();

        }

        Double ConsoParMetre = consototal / surfacetotalhabitation;
        Double ProdParMetre = prodtotal / surfacetotalbepos;
        Double EtatEnergetique = -1.0;
        if (ConsoParMetre < ProdParMetre) {
            EtatEnergetique = 1.0;
        }


        Map<String, Double> habitationresum = new HashMap<>();
        habitationresum.put("consommation", consototal);
        habitationresum.put("Production", prodtotal);
        habitationresum.put("ConsoParMetre", ConsoParMetre);
        habitationresum.put("ProdParMetre", ProdParMetre);
        habitationresum.put("EtatEnergetique", EtatEnergetique);
        habitationresum.put("diffconsoprod", ConsoParMetre - ProdParMetre);
        habitationresum.put("surfacetotalhabitation", surfacetotalhabitation);
        habitationresum.put("surfacetotalbepos", surfacetotalbepos);

        return habitationresum;
    }


    public Habitation getHabitationByInhabitantId(Long iduser){
        return habitationRepository.findByIdInhabitant(iduser);
    }

    public Long getHabitationByInhabitantId2(Long iduser){
        return habitationRepository.findByIdInhabitant2(iduser);
    }

}
