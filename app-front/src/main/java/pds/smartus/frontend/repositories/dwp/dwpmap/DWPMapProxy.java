package pds.smartus.frontend.repositories.dwp.dwpmap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pds.smartus.frontend.entities.dwp.dwpmap.Building;
import pds.smartus.frontend.entities.dwp.dwpmap.DWP;
import pds.smartus.frontend.entities.dwp.dwpmap.DWP_Room;
import pds.smartus.frontend.entities.dwp.dwpmap.Room;
import pds.smartus.frontend.properties.CustomProperties;

import java.util.List;

@Service
public class DWPMapProxy {

    @Autowired
    private CustomProperties properties;
  

    public DWP getDWP(int id_dwp) {
        String url=properties.getApiUrl()+"/dwp/"+id_dwp;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DWP> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<DWP>() {}
        );

      return response.getBody();

    }

    public Room addRoom(Room r){
        String url=properties.getApiUrl()+"/room";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Room> request = new HttpEntity<Room>(r);
        ResponseEntity<Room> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<Room>() {}
        );
        return response.getBody();
    }

    public void addDWPRoom(DWP_Room dwproom){

        String url=properties.getApiUrl()+"/dwproom";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<DWP_Room> request = new HttpEntity<DWP_Room>(dwproom);
        ResponseEntity<DWP_Room> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<DWP_Room>() {}
        );

    }

    public void setDWPConfigured(boolean b, int id_dwp) {
        String url=properties.getApiUrl()+"/"+id_dwp+"/"+b;
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                null,
                Void.class
        );

    }

    public List<Integer> getListRoomIdByArea(int id_dwp, String area_type) {
        String url=properties.getApiUrl()+"/dwproom/"+id_dwp+"/"+area_type;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Integer>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Integer>>() {}
        );

        return response.getBody();
    }

    public Room getRoom(int id_room) {
        String url=properties.getApiUrl()+"/roombyarea/"+id_room;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Room> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Room>() {}
        );

        return response.getBody();
    }

    public List<Integer> getListRoomIdByIdDWP(int id_dwp) {
        String url=properties.getApiUrl()+"/dwproom/"+id_dwp;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Integer>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Integer>>() {}
        );

        return response.getBody();
    }

    public List<Integer> deleteDWPRoomById(int id_dwp) {
        String url=properties.getApiUrl()+"/dwproom/"+id_dwp;
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Void.class
        );
        return null;
    }

    public void deleteRoom(int id_room) {
        String url=properties.getApiUrl()+"/room/"+id_room;
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }

    public List<Building> getBuildings() {
        String url=properties.getApiUrl()+"/buildings/";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Building>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Building>>() {}
        );

        return response.getBody();
    }

    public List<DWP> getDWPsByIdBuilding(int id_building,boolean configured) {
        String url=properties.getApiUrl()+"/dwp/building/"+id_building+"/"+configured;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<DWP>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DWP>>() {}
        );
        return response.getBody();
    }
    public List<DWP> getDWPsByIdBuilding(int id_building) {
        String url=properties.getApiUrl()+"/dwp/building/"+id_building;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<DWP>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DWP>>() {}
        );
        return response.getBody();
    }

    public String getBuildingName(int id_building) {
        String url=properties.getApiUrl()+"/dwp/building/name/"+id_building;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                String.class
        );
        return response.getBody();
    }

    public Building getBuildingById(int id_building) {
        String url=properties.getApiUrl()+"/building/"+id_building;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Building> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Building>() {}
        );
        return response.getBody();
    }

    public DWP_Room getSingleDWPRoom(int id_dwp) {
        String url=properties.getApiUrl()+"/dwproom/single/"+id_dwp;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DWP_Room> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<DWP_Room>() {}
        );
        return response.getBody();
    }
}
