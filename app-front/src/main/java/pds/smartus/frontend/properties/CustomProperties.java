package pds.smartus.frontend.properties;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix="pds.smartus.backend")
public class CustomProperties {

    @Value("apiUrl")
    private String apiUrl;

}

