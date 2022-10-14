package pds.smartus.frontend.repositories.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pds.smartus.frontend.entities.dwp.dwpmap.Room;
import pds.smartus.frontend.properties.CustomProperties;

@Service
public class RoomTestProxy {
    @Autowired
    private CustomProperties properties;

    public Room getRoomTest(int id_room) {
        String url=properties.getApiUrl()+"/getRoom/"+id_room;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Room> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Room>() {}
        );

        return response.getBody();
    }
}
