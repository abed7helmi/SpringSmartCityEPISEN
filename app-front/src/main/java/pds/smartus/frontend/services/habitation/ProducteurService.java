package pds.smartus.frontend.services.habitation;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.smartus.frontend.entities.habitation.Producteur;
import pds.smartus.frontend.repositories.habitation.ProducteurProxy;

@Data
@Service
public class ProducteurService {

    @Autowired
    private ProducteurProxy producteurProxy;

    public Iterable<Producteur> getProducteurs(Long beposid){
        return producteurProxy.getProducteurs(beposid);
    }
}
