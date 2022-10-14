package pds.smartus.frontend.entities.habitation;

import lombok.Data;

@Data
public class Alert {
    private Long id;
    private String sender;
    private String contents;
    private Bepos bepos;

}
