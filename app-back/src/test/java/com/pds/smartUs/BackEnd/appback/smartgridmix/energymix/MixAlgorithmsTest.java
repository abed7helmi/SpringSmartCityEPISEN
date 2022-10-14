package com.pds.smartUs.BackEnd.appback.smartgridmix.energymix;

import com.pds.smartUs.BackEnd.appback.services.smartgrid.mixalgos.DefaultAlgoService;
import com.pds.smartUs.BackEnd.appback.simulator.powersites.GeothermalPowerSite;
import com.pds.smartUs.BackEnd.appback.simulator.powersites.HydraulicPowerSite;
import com.pds.smartUs.BackEnd.appback.simulator.powersites.SolarPowerSite;
import com.pds.smartUs.BackEnd.appback.simulator.powersites.WindPowerSite;
import com.pds.smartUs.BackEnd.appback.smartgridmix.MixResponse;
import com.pds.smartUs.BackEnd.appback.smartgridmix.SmartGridRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.LinkedHashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
class MixAlgorithmsTest {

    @Autowired
    private MixAlgorithms mixAlgorithms;

    @Test
    void shouldReturnResultOfSimpleAlgorithm(){
        //
        // GIVEN
        //
        SmartGridRequest smartGridRequest = new SmartGridRequest(100, new WindPowerSite(), new SolarPowerSite(), new HydraulicPowerSite(), new GeothermalPowerSite(), "15h22");
        LinkedHashMap<String, Integer> energiesPosition = new LinkedHashMap<>();
        energiesPosition.put("wind",1);
        energiesPosition.put("solar",2);
        energiesPosition.put("hydraulic",3);
        energiesPosition.put("geothermal",4);

        smartGridRequest.getWindPowerSite().getEnergyProduction().setAmount(40F);
        smartGridRequest.getSolarPowerSite().getEnergyProduction().setAmount(50F);
        smartGridRequest.getHydraulicPowerSite().getEnergyProduction().setAmount(30F);
        smartGridRequest.getGeothermalPowerSite().getEnergyProduction().setAmount(10F);


        //
        // WHEN
        //
        MixResponse mixResponse = mixAlgorithms.simpleAlgorithm(smartGridRequest, energiesPosition);

        //
        // THEN
        //

        assertEquals(mixResponse.getWindEnergyAmount().getAmount(), 40F);
        assertEquals(mixResponse.getSolarEnergyAmount().getAmount(), 50F);
        assertEquals(mixResponse.getHydraulicEnergyAmount().getAmount(), 10F);
        assertEquals(mixResponse.getGeothermalEnergyAmount().getAmount(), 0F);

    }

    @Test
    void shouldReturnResultOfPercentageAlgorithm(){
        //
        // GIVEN
        //
        SmartGridRequest smartGridRequest = new SmartGridRequest(100, new WindPowerSite(), new SolarPowerSite(), new HydraulicPowerSite(), new GeothermalPowerSite(), "15h22");
        LinkedHashMap<String, Integer> energiesPercentage = new LinkedHashMap<>();
        energiesPercentage.put("wind",25);
        energiesPercentage.put("solar",25);
        energiesPercentage.put("hydraulic",25);
        energiesPercentage.put("geothermal",25);

        smartGridRequest.getWindPowerSite().getEnergyProduction().setAmount(40F);
        smartGridRequest.getSolarPowerSite().getEnergyProduction().setAmount(50F);
        smartGridRequest.getHydraulicPowerSite().getEnergyProduction().setAmount(30F);
        smartGridRequest.getGeothermalPowerSite().getEnergyProduction().setAmount(40F);


        //
        // WHEN
        //
        MixResponse mixResponse = mixAlgorithms.percentageAlgorithm(smartGridRequest, energiesPercentage);

        //
        // THEN
        //


        assertEquals(mixResponse.getWindEnergyAmount().getAmount(), 25F);
        assertEquals(mixResponse.getSolarEnergyAmount().getAmount(), 25F);
        assertEquals(mixResponse.getHydraulicEnergyAmount().getAmount(), 25F);
        assertEquals(mixResponse.getGeothermalEnergyAmount().getAmount(), 25F);

    }

    @Test
    void shouldReturnResultOfEnvironmentalAlgorithm(){
        //
        // GIVEN
        //
        SmartGridRequest smartGridRequest = new SmartGridRequest(100, new WindPowerSite(), new SolarPowerSite(), new HydraulicPowerSite(), new GeothermalPowerSite(), "15h22");


        smartGridRequest.getWindPowerSite().getEnergyProduction().setAmount(40F);
        smartGridRequest.getSolarPowerSite().getEnergyProduction().setAmount(50F);
        smartGridRequest.getHydraulicPowerSite().getEnergyProduction().setAmount(30F);
        smartGridRequest.getGeothermalPowerSite().getEnergyProduction().setAmount(40F);


        //
        // WHEN
        //
        MixResponse mixResponse = mixAlgorithms.environmentalAlgorithm(smartGridRequest);

        //
        // THEN
        //

        System.out.println(mixResponse.getWindEnergyAmount().getAmount());
        System.out.println(mixResponse.getSolarEnergyAmount().getAmount());
        System.out.println(mixResponse.getHydraulicEnergyAmount().getAmount());
        System.out.println(mixResponse.getGeothermalEnergyAmount().getAmount());

        assertEquals(mixResponse.getWindEnergyAmount().getAmount(), 40F);
        assertEquals(mixResponse.getSolarEnergyAmount().getAmount(), 30F);
        assertEquals(mixResponse.getHydraulicEnergyAmount().getAmount(), 30F);
        assertEquals(mixResponse.getGeothermalEnergyAmount().getAmount(), 0F);

    }

    @Test
    void shouldReturnResultOfEconomicAlgorithm(){
        //
        // GIVEN
        //
        SmartGridRequest smartGridRequest = new SmartGridRequest(135, new WindPowerSite(), new SolarPowerSite(), new HydraulicPowerSite(), new GeothermalPowerSite(), "15h22");


        smartGridRequest.getWindPowerSite().getEnergyProduction().setAmount(40F);
        smartGridRequest.getSolarPowerSite().getEnergyProduction().setAmount(50F);
        smartGridRequest.getHydraulicPowerSite().getEnergyProduction().setAmount(135F);
        smartGridRequest.getGeothermalPowerSite().getEnergyProduction().setAmount(40F);


        //
        // WHEN
        //
        MixResponse mixResponse = mixAlgorithms.economicAlgorithm(smartGridRequest);

        //
        // THEN
        //

        assertEquals(mixResponse.getWindEnergyAmount().getAmount(), 1F);
        assertEquals(mixResponse.getSolarEnergyAmount().getAmount(), 16F);
        assertEquals(mixResponse.getHydraulicEnergyAmount().getAmount(), 118F);
        assertEquals(mixResponse.getGeothermalEnergyAmount().getAmount(), 0F);

    }

}