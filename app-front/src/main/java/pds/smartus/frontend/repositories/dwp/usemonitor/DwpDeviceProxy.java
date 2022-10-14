package pds.smartus.frontend.repositories.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pds.smartus.frontend.entities.dwp.usemonitor.DwpDevice;
import pds.smartus.frontend.properties.CustomProperties;

import java.util.HashMap;
import java.util.Map;

@Service
public class DwpDeviceProxy {

    @Autowired
    private CustomProperties properties;

    public Iterable<DwpDevice> getEquipments(){
        String getEquipmentsUrl = properties.getApiUrl()+"/devices/";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<DwpDevice>> response = restTemplate.exchange(
                getEquipmentsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<DwpDevice>>() {}
        );
        return response.getBody();
    }

    public DwpDevice getEquipmentById(int equipmentId) {
        String getEquipmentUrl = properties.getApiUrl() +"/devices/" + equipmentId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DwpDevice> response = restTemplate.exchange(
                getEquipmentUrl,
                HttpMethod.GET,
                null,
                DwpDevice.class
        );
        return response.getBody();
    }

    public Iterable<DwpDevice> getEquipmentByRoom(int roomId) {
        String getEquipmentUrl = properties.getApiUrl() +"devices/room/" + roomId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<DwpDevice>> response = restTemplate.exchange(
                getEquipmentUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<DwpDevice>>() {});
        return response.getBody();
    }

    public Integer getPC(int roomId) {
        String getPCUrl = properties.getApiUrl() +"/devices/PC/" + roomId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Integer> response = restTemplate.exchange(
                getPCUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Integer>() {});
        return response.getBody();
    }
}
