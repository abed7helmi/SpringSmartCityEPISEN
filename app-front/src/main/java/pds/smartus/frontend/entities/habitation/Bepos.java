package pds.smartus.frontend.entities.habitation;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Bepos {
    private Long id;
    private String name;
    private String adresse;
    private List<Habitation> habitations = new ArrayList<>();
    private List<Producteur> producteurs = new ArrayList<>();
    private List<Alert> alerts = new ArrayList<>();


}
