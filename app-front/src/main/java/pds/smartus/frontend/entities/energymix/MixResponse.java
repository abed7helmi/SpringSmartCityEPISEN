package pds.smartus.frontend.entities.energymix;

import lombok.Data;

@Data
public class MixResponse {

    private int id;

    private float amountToProduce;

    private String date;

    private float windAmount;

    private float solarAmount;

    private float hydraulicAmount;

    private float geothermalAmount;
}
