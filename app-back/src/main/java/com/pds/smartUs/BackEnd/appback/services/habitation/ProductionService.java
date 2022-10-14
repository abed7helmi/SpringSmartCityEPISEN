package com.pds.smartUs.BackEnd.appback.services.habitation;

import com.pds.smartUs.BackEnd.appback.entities.Producteur;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.ProducteurRepository;
import com.pds.smartUs.BackEnd.appback.responses.DashResponseY;
import com.pds.smartUs.BackEnd.appback.entities.Production;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.ProductionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

@Service
@AllArgsConstructor
public class ProductionService {

    private final ProductionRepository productionRepository;
    private final ProducteurRepository producteurRepository;
    static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;



    public List<Producteur> getProducteursBybepos (Long beposid){
        return producteurRepository.findAllByBeposId(beposid);
    }


    public Map<String, List<Production>> getProductionsOfProducers(Long beposid){

        List<Producteur> prods = this.getProducteursBybepos(beposid);
        List<Production> production = productionRepository.findProductionsByTime(prods.size()*10,beposid);

        Map<String, List<Production>> productionsForEachProducteur = new HashMap<>();
        production.stream()
                .forEach(productionWithProducteur -> {
                    List productions = productionsForEachProducteur.get(productionWithProducteur.getProducteur().getModel());
                    if (productions == null)
                        productions = new ArrayList();
                    productions.add(productionWithProducteur);
                    productionsForEachProducteur.put(productionWithProducteur.getProducteur().getModel(), productions );
                });

        return productionsForEachProducteur;
    }


    public List<Double> getProductionsOfProducersBydate(Long beposid,ArrayList<LocalDate> dates){


        List<Double> result = new ArrayList<>();

        for (int i = 0; i < dates.size()-1; i++) {
            Double x=productionRepository.findProductionsByDate(beposid,dates.get(i+1),dates.get(i));
            result.add(x);
        }



        return result;
    }







}
