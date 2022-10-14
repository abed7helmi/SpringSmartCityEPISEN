package com.pds.smartUs.BackEnd.appback.entities.smartgridmix;

import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.*;

@Entity
@Table(name = "mix_responses")
public class MixResponses {

    @Id
    @Column(name = "mix_response_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "amount_to_produce")
    private float amountToProduce;

    @Column(name = "date_")
    private String date;

    @Column(name = "wind_amount")
    private float windAmount;

    @Column(name = "solar_amount")
    private float solarAmount;

    @Column(name = "hydraulic_amount")
    private float hydraulicAmount;

    @Column(name = "geothermal_amount")
    private float geothermalAmount;


    public MixResponses(float amountToProduce, String date, float windAmount, float solarAmount, float hydraulicAmount, float geothermalAmount) {
        this.amountToProduce = amountToProduce;
        this.date = date;
        this.windAmount = windAmount;
        this.solarAmount = solarAmount;
        this.hydraulicAmount = hydraulicAmount;
        this.geothermalAmount = geothermalAmount;
    }

    public MixResponses(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmountToProduce() {
        return amountToProduce;
    }

    public void setAmountToProduce(float amountToProduce) {
        this.amountToProduce = amountToProduce;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getWindAmount() {
        return windAmount;
    }

    public void setWindAmount(float windAmount) {
        this.windAmount = windAmount;
    }

    public float getSolarAmount() {
        return solarAmount;
    }

    public void setSolarAmount(float solarAmount) {
        this.solarAmount = solarAmount;
    }

    public float getHydraulicAmount() {
        return hydraulicAmount;
    }

    public void setHydraulicAmount(float hydraulicAmount) {
        this.hydraulicAmount = hydraulicAmount;
    }

    public float getGeothermalAmount() {
        return geothermalAmount;
    }

    public void setGeothermalAmount(float geothermalAmount) {
        this.geothermalAmount = geothermalAmount;
    }

    @Override
    public String toString() {
        return "MixResponses{" +
                "id=" + id +
                ", amountToProduce=" + amountToProduce +
                ", date='" + date + '\'' +
                ", windAmount=" + windAmount +
                ", solarAmount=" + solarAmount +
                ", hydraulicAmount=" + hydraulicAmount +
                ", geothermalAmount=" + geothermalAmount +
                '}';
    }
}
