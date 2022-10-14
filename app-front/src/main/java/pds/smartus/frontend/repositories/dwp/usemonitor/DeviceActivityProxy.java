package pds.smartus.frontend.repositories.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pds.smartus.frontend.entities.dwp.usemonitor.DeviceActivity;
import pds.smartus.frontend.properties.CustomProperties;

@Service
public class DeviceActivityProxy {

    @Autowired
    private CustomProperties properties;

    public DeviceActivity addNewDeviceActivity(DeviceActivity deviceActivity, int equipment_id) {
        String equipment = String.valueOf(equipment_id);
        String createActivityUrl = properties.getApiUrl() +"/deviceactivity/" +equipment;
        HttpEntity<DeviceActivity> request = new HttpEntity<DeviceActivity>(deviceActivity);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DeviceActivity> response = restTemplate.exchange(
                createActivityUrl,
                HttpMethod.POST,
                request,
                DeviceActivity.class
        );
        return response.getBody();
    }

    public Iterable<DeviceActivity> getDeviceActivities(){
        String getActivitiesUrl = properties.getApiUrl()+"/deviceactivity";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<DeviceActivity>> response = restTemplate.exchange(
                getActivitiesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<DeviceActivity>>() {}
        );
        return response.getBody();
    }

    public DeviceActivity getLastActivity(int equipmentId) {
        String equipment = String.valueOf(equipmentId);
        String getLastActivitytUrl = properties.getApiUrl() +"/deviceactivity/last/" + equipment;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DeviceActivity> response = restTemplate.exchange(
                getLastActivitytUrl,
                HttpMethod.GET,
                null,
                DeviceActivity.class
        );
        return response.getBody();
    }

}
