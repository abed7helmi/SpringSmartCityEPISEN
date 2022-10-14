package pds.smartus.frontend.repositories.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pds.smartus.frontend.properties.CustomProperties;

@Service
public class SensorTempProxy {
    @Autowired
    private CustomProperties properties;

    public String getRoomTemp(int id_room){
        String getRoomTempUrl = properties.getApiUrl()+"/roomtemp/"+id_room;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                getRoomTempUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    public String getDateTemp(int id_room){
        String getDateTempUrl = properties.getApiUrl()+"/roomtemp/date/"+id_room;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                getDateTempUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

}
