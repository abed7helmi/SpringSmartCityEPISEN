package pds.smartus.frontend.entities.habitation;

import lombok.Data;

import java.util.Collection;

@Data
public class Producteur {
    Long id;
    String maker;
    String model;
    String type;
    String power;
    String state;
    private Bepos bepos;
    Collection<Production> productions;
}
