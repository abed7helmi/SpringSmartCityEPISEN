package pds.smartus.frontend.repositories.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pds.smartus.frontend.entities.dwp.usemonitor.User;
import pds.smartus.frontend.properties.CustomProperties;

@Service
public class UserProxy {

    @Autowired
    private CustomProperties properties;

    public Boolean CheckUser(String name) {
        String getUserUrl = properties.getApiUrl()+"/User/checkUser?name="+name;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.exchange(
                getUserUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Boolean>() {
                }
        );
        return response.getBody();
    }

    public User getUserById(int userId) {
        String getUserUrl = properties.getApiUrl() +"/User/" + userId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> response = restTemplate.exchange(
                getUserUrl,
                HttpMethod.GET,
                null,
                User.class
        );
        return response.getBody();
    }
}
