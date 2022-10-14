package pds.smartus.frontend.entities.habitation;

import lombok.Data;

@Data

public class Inhabitant {

    private Long idInhabitant;
    private String inhabitantFirstName;
    private String inhabitantLastName;
    private String inhabitantEmail;
    private String inhabitantPassword;
    private Habitation habitation;

}
