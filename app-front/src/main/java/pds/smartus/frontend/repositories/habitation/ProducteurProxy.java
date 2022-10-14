package pds.smartus.frontend.repositories.habitation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pds.smartus.frontend.entities.habitation.Producteur;

@Service
public class ProducteurProxy {

    @Autowired
    private Environment env;


    public Iterable<Producteur> getProducteurs(Long beposid){
        String url = env.getProperty("pds.smartus.backend.apiUrl");
        String getProducteursUrl = url+"listProducteurs/"+beposid;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Producteur>> response = restTemplate.exchange(
                getProducteursUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Producteur>>() {}
        );

        return response.getBody();


    }



}
