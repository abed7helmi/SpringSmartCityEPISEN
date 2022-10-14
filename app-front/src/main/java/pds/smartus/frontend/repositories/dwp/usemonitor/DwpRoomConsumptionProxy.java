package pds.smartus.frontend.repositories.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pds.smartus.frontend.properties.CustomProperties;

@Service
public class DwpRoomConsumptionProxy {

    @Autowired
    private CustomProperties properties;

    public Iterable<Object> getInfosConsoByRoom(){
        String getConsoDevicesByRoomUrl = properties.getApiUrl()+"/consoRoom";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Object>> response = restTemplate.exchange(
                getConsoDevicesByRoomUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

}
