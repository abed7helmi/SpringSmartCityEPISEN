package pds.smartus.frontend.repositories.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pds.smartus.frontend.entities.dwp.usemonitor.DwpDeviceConsumption;
import pds.smartus.frontend.properties.CustomProperties;

@Service
public class DwpDeviceConsumptionProxy {
    @Autowired
    private CustomProperties properties;

    public Iterable<Object> getConsoDevicesByRoom(int roomId){
        String getConsoDevicesByRoomUrl = properties.getApiUrl()+"/consoDevices/room/"+roomId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Object>> response = restTemplate.exchange(
                getConsoDevicesByRoomUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    public Float getConsoByDeviceId(int roomId){
        String getConsoDevicesByRoomUrl = properties.getApiUrl()+"/conso/device/"+roomId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Float> response = restTemplate.exchange(
                getConsoDevicesByRoomUrl,
                HttpMethod.GET,
                null,
                Float.class
        );
        return response.getBody();
    }

}
