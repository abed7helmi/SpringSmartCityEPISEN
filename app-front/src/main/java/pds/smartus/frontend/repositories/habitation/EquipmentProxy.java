package pds.smartus.frontend.repositories.habitation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pds.smartus.frontend.entities.habitation.Equipment;


@Service
public class EquipmentProxy {

    @Autowired
    private Environment env;


    public Iterable<Equipment> getEquipments(Long roomid){

        String url = env.getProperty("pds.smartus.backend.apiUrl");
        String getProducteursUrl = url+"listEquipments/"+roomid;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Equipment>> response = restTemplate.exchange(
                getProducteursUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Equipment>>() {}
        );


        return response.getBody();
    }



}
