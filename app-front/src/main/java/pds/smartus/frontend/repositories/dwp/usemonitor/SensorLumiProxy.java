package pds.smartus.frontend.repositories.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pds.smartus.frontend.properties.CustomProperties;

@Service
public class SensorLumiProxy {
    @Autowired
    private CustomProperties properties;

    public Integer getRoomLumi(int id_room){
        String getRoomLumiUrl = properties.getApiUrl()+"/roomlumi/"+id_room;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Integer> response = restTemplate.exchange(
                getRoomLumiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }
    public Integer getOutdoorLumi(int id_room){
        String getRoomLumiUrl = properties.getApiUrl()+"/roomlumi/out/"+id_room;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Integer> response = restTemplate.exchange(
                getRoomLumiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }
}
