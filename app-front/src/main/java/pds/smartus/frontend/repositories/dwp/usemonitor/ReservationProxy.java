package pds.smartus.frontend.repositories.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pds.smartus.frontend.entities.dwp.usemonitor.Reservation;
import pds.smartus.frontend.entities.dwp.usemonitor.ReservationTest;
import pds.smartus.frontend.properties.CustomProperties;

@Service
public class ReservationProxy {
    @Autowired
    private CustomProperties properties;

    public Iterable<Reservation> getReservations(){
        String getReservationsUrl = properties.getApiUrl()+"/resa";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Reservation>> response = restTemplate.exchange(
                getReservationsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Reservation>>() {}
        );
        return response.getBody();
    }

    public Iterable<ReservationTest> getReservationsTest(){
        String getReservationsUrl = properties.getApiUrl()+"/resa/test";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<ReservationTest>> response = restTemplate.exchange(
                getReservationsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<ReservationTest>>() {}
        );
        return response.getBody();
    }

    public Reservation getReservationById(int resaId) {
        String getResaUrl = properties.getApiUrl() +"/resa/" + resaId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Reservation> response = restTemplate.exchange(
                getResaUrl,
                HttpMethod.GET,
                null,
                Reservation.class
        );
        return response.getBody();
    }
    public String getNextAvailableDate(int roomId) {
        String getResaUrl = properties.getApiUrl() +"/resa/available/" + roomId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                getResaUrl,
                HttpMethod.GET,
                null,
                String.class
        );
        return response.getBody();
    }

    public boolean isAvailableNow(int roomId) {
        String getResaUrl = properties.getApiUrl() +"/resa/available/now/" + roomId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.exchange(
                getResaUrl,
                HttpMethod.GET,
                null,
                Boolean.class
        );
        return response.getBody();
    }
}
