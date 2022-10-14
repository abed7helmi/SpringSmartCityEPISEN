package pds.smartus.frontend.repositories.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pds.smartus.frontend.properties.CustomProperties;

@Service
public class RoomConsumptionProxy {

    @Autowired
    private CustomProperties properties;

    public Float getRoomConsumption(int idroom){
        String getRoomConsumptionUrl = properties.getApiUrl()+"/consumption/room/"+idroom;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Float> response = restTemplate.exchange(
                getRoomConsumptionUrl,
                HttpMethod.GET,
                null,
                Float.class
        );
        return response.getBody();
    }
}
