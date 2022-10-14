package pds.smartus.frontend.repositories.habitation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pds.smartus.frontend.entities.habitation.Habitation;
import pds.smartus.frontend.entities.habitation.Inhabitant;

@Service
public class HabitationProxy {

    @Autowired
    private Environment env;

    public Habitation getHabitation(int id){

        String url = env.getProperty("pds.smartus.backend.apiUrl");
        String getUrl = url+"home/"+id;
        System.out.println(getUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Habitation> response = restTemplate.exchange(
                getUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Habitation>() {}
        );

        System.out.println(response);

        return response.getBody();
    }

    public Inhabitant getInhabitantCnx(String email,String password){

        String url = env.getProperty("pds.smartus.backend.apiUrl");
        String getUrl = url+"login/inhabitantcnx/"+email+"/"+password;
        System.out.println(getUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Inhabitant> response = restTemplate.exchange(
                getUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Inhabitant>() {}
        );
        System.out.println(response);
        return response.getBody();
    }

    public Long getidhabitation(Long iduser){

        String url = env.getProperty("pds.smartus.backend.apiUrl");
        String getUrl = url+"habitationid/"+iduser;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Long> response = restTemplate.exchange(
                getUrl,
                HttpMethod.GET,
                null,
                Long.class
        );
        return response.getBody();
    }





}
