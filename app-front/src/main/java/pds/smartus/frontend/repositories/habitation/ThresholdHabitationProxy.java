package pds.smartus.frontend.repositories.habitation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import org.springframework.web.client.RestTemplate;


import pds.smartus.frontend.entities.habitation.Habitation;
import pds.smartus.frontend.properties.CustomProperties;


@Component
public class ThresholdHabitationProxy {

    @Autowired
    private CustomProperties properties;

    public Habitation getHabitation(){
        String getUrl = "http://localhost:8083";
        System.out.println(getUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Habitation> response = restTemplate.exchange(
                getUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Habitation>() {}
        );

        System.out.println(response);

        return response.getBody();
    }


    public Habitation getHouseById(int id_habitation) {
        String getHouseUrl = "http://localhost:8083/threshold/thresholdHouse/" + id_habitation;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Habitation> response = restTemplate.exchange(
                getHouseUrl,
                HttpMethod.GET,
                null,
                Habitation.class
        );
        return response.getBody();
    }

}
