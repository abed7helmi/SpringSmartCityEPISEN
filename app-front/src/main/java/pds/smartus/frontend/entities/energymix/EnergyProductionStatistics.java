package pds.smartus.frontend.entities.energymix;

import lombok.Data;

@Data
public class EnergyProductionStatistics {

    private int id;

    private String latestDate;

    private float ennrProd;

    private float solarProd;

    private float windProd;

    private float hydraulicProd;

    private float geothermalProd;
}
