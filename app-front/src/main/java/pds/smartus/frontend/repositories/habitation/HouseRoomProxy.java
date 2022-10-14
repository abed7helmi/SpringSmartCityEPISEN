package pds.smartus.frontend.repositories.habitation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pds.smartus.frontend.entities.habitation.HouseRoom;

@Service
public class HouseRoomProxy {

    @Autowired
    private Environment env;

    public Iterable<HouseRoom> getRooms(Long habitationid){
        String url = env.getProperty("pds.smartus.backend.apiUrl");
        String getProducteursUrl = url+"listRooms/"+habitationid;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<HouseRoom>> response = restTemplate.exchange(
                getProducteursUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<HouseRoom>>() {}
        );

        return response.getBody();
    }
}
