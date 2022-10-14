package com.pds.smartUs.BackEnd.appback.controllers.habitation;

import com.pds.smartUs.BackEnd.appback.entities.Producteur;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.ProducteurRepository;
import com.pds.smartUs.BackEnd.appback.services.habitation.ProductionService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProducteurRestController {

    private final ProducteurRepository producteurRepository;

    private final ProductionService productionService;

    //    @GetMapping("/listProducteurs")
    @GetMapping("/listProducteurs/{beposid}")
    public List<Producteur> list(@PathVariable Long beposid) {

        return productionService.getProducteursBybepos(beposid);
    }


    @GetMapping("/productions/{beposid}")
    public ResponseEntity<?> listProd(@PathVariable Long beposid) {
        return ResponseEntity.ok(productionService.getProductionsOfProducers(beposid));
    }

//    @GetMapping("/productionsbydate/{beposid}")
//    public ResponseEntity<?> listProdBydate(@PathVariable Long beposid) {
//        return ResponseEntity.ok(productionService.getProductionsOfProducersBydate(beposid));
//    }

    @GetMapping("/producteurs/{id}")
    public Producteur getOne(@PathVariable Long id){return producteurRepository.findById(id).get();}


}
