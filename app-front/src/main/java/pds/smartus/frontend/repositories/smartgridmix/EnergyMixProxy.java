package pds.smartus.frontend.repositories.smartgridmix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pds.smartus.frontend.entities.energymix.EnergyProductionStatistics;
import pds.smartus.frontend.entities.energymix.MixResponse;
import pds.smartus.frontend.properties.CustomProperties;

@Service
public class EnergyMixProxy {

    @Autowired
    private CustomProperties customProperties;

    public Iterable<MixResponse> getLatestMixResult(){
        final String getStatsUrl = customProperties.getApiUrl() + "/energy-mix/latest-mix-result";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<MixResponse>> response = restTemplate.exchange(
                getStatsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<MixResponse>>() {}
        );
        return response.getBody();
    }

    public void activateEconomicAlgo(){
        final String getStatsUrl = customProperties.getApiUrl() + "/energy-mix/activate-economic-algo";
        System.out.println(getStatsUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.exchange(
                getStatsUrl,
                HttpMethod.POST,
                null,
                Void.class
        );
    }

}
