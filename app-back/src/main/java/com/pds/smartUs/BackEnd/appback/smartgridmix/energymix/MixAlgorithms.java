package com.pds.smartUs.BackEnd.appback.smartgridmix.energymix;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.mixalgos.EconomicAlgo;
import com.pds.smartUs.BackEnd.appback.helpers.Helper;
import com.pds.smartUs.BackEnd.appback.services.smartgrid.mixalgos.EconomicAlgoService;
import com.pds.smartUs.BackEnd.appback.simulator.enums.EnergyType;
import com.pds.smartUs.BackEnd.appback.smartgridmix.AmountToProduce;
import com.pds.smartUs.BackEnd.appback.smartgridmix.MixResponse;
import com.pds.smartUs.BackEnd.appback.smartgridmix.SmartGridRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.Function;

import java.util.function.Function;


@Component
public class MixAlgorithms {

    @Autowired
    private EconomicAlgoService economicAlgoService;

    private float amountToProduce;

    private float windPowerCapacity;
    private float solarPowerCapacity;
    private float hydroPowerCapacity;
    private float geothermalPowerCapacity;

    private AmountToProduce windPower;
    private AmountToProduce solarPower;
    private AmountToProduce hydraulicPower;
    private AmountToProduce geothermalPower;



    /**
     *
     * @param smartGridRequest
     * @return the result of the simple Algorithm
     */

    public MixResponse simpleAlgorithm(SmartGridRequest smartGridRequest, HashMap<String, Integer> energiesPosition){

        float tempProd = 0;
        loadData(smartGridRequest);

        HashMap<String, AmountToProduce> amountToProduceHashMap = new HashMap<>();
        HashMap<String, Float> capacityHashMap = new HashMap<>();

        amountToProduceHashMap.put("wind", windPower);
        amountToProduceHashMap.put("solar", solarPower);
        amountToProduceHashMap.put("hydraulic", hydraulicPower);
        amountToProduceHashMap.put("geothermal", geothermalPower);

        capacityHashMap.put("wind", windPowerCapacity);
        capacityHashMap.put("solar", solarPowerCapacity);
        capacityHashMap.put("hydraulic", hydroPowerCapacity);
        capacityHashMap.put("geothermal", geothermalPowerCapacity);


        for (Object key : energiesPosition.keySet()) {
            String lKey = (String) key;
            tempProd = repartionOfProduction(tempProd,amountToProduce,amountToProduceHashMap.get(lKey),capacityHashMap.get(lKey));
        }


        return new MixResponse(windPower,solarPower,hydraulicPower,geothermalPower);
    }

    /**
     *
     * @param smartgridRequest
     * @return the result of the percentage algorithm
     */
    public MixResponse percentageAlgorithm(SmartGridRequest smartgridRequest, HashMap<String,Integer> energiesPercentage){

        float tempProd = 0;
        loadData(smartgridRequest);

        HashMap<String, Integer> energyPrioritization = new HashMap<>();
        LinkedHashMap<String, Integer> sortedEnergiesPercentage = (LinkedHashMap<String, Integer>) Helper.sortMapInt(energiesPercentage);


        // First, we compute the amount of each type of energy according to percentage given by the energy regulation service
        float windToProduce = (float) (energiesPercentage.get("wind")/100.0) * amountToProduce;
        float solarToProduce = ((float) (energiesPercentage.get("solar")/100.0)) * amountToProduce;
        float hydraulicToProduce = ((float) (energiesPercentage.get("hydraulic")/100.0)) * amountToProduce;
        float geothermalToProduce = ((float) (energiesPercentage.get("geothermal")/100.0)) * amountToProduce;

        //Then, we see if each type of energy can cover the needs
        float[] windFirstResult = percentageAlgoHelper(tempProd,windToProduce, windPowerCapacity, windPower);
        tempProd = windFirstResult[0]; windToProduce = windFirstResult[1]; windPowerCapacity = windFirstResult[2];

        float[] solarFirstResult = percentageAlgoHelper(tempProd,solarToProduce, solarPowerCapacity, solarPower);
        tempProd = solarFirstResult[0]; solarToProduce = solarFirstResult[1]; solarPowerCapacity = solarFirstResult[2];

        float[] hydraulicFirstResult = percentageAlgoHelper(tempProd,hydraulicToProduce, hydroPowerCapacity, hydraulicPower);
        tempProd = hydraulicFirstResult[0]; hydraulicToProduce = hydraulicFirstResult[1]; hydroPowerCapacity = hydraulicFirstResult[2];

        float[] geothermalFirstResult = percentageAlgoHelper(tempProd,geothermalToProduce, geothermalPowerCapacity, geothermalPower);
        tempProd = geothermalFirstResult[0]; geothermalToProduce = geothermalFirstResult[1]; geothermalPowerCapacity = geothermalFirstResult[2];

        // Finally, if an energy can't cover the requested need, another will do it

        tempProd = percentageAlgoHelperBis(tempProd, amountToProduce ,windPower, windPowerCapacity);
        tempProd = percentageAlgoHelperBis(tempProd, amountToProduce, solarPower, solarPowerCapacity);
        tempProd = percentageAlgoHelperBis(tempProd, amountToProduce, hydraulicPower, hydroPowerCapacity);
        tempProd = percentageAlgoHelperBis(tempProd, amountToProduce, geothermalPower, geothermalPowerCapacity);

        for (int i = 0; i<sortedEnergiesPercentage.size(); i++){
            energyPrioritization.put((String) sortedEnergiesPercentage.keySet().toArray()[i],sortedEnergiesPercentage.size()-i);
        }

        System.out.println("Amount " + amountToProduce);
        return new MixResponse(windPower,solarPower,hydraulicPower,geothermalPower);
    }


    /**
     *
     * @param smartGridRequest
     * @return the result of the economic algorithm
     */
    public MixResponse economicAlgorithm(SmartGridRequest smartGridRequest){

        float tempProd = 0;
        loadData(smartGridRequest);

        ArrayList<Float> windCosts = new ArrayList<>();
        windCosts.add(0.0F);
        ArrayList<Float> solarCosts = new ArrayList<>();
        solarCosts.add(0.0F);
        ArrayList<Float> geothermalCosts = new ArrayList<>();
        geothermalCosts.add(0.0F);
        ArrayList<Float> hydraulicCosts = new ArrayList<>();
        hydraulicCosts.add(0.0F);

        for(int i=1; i<(int) windPowerCapacity; i++){
            windCosts.add((float) (4 + Math.log(10*i)));
        }

        for(int i=1; i<(int) solarPowerCapacity; i++){
            solarCosts.add((float) (5 * (Math.exp(-(i/4))) + 8));
        }

        for(int i=1; i<(int) hydroPowerCapacity; i++){
            hydraulicCosts.add((((float)-i/36) + 11));
        }


        float minVal = Float.MAX_VALUE;

        for (int i = 1; i<windCosts.size();i++){
            for (int j = 1 ; j<solarCosts.size(); j++){
                for (int k = 1 ; k<hydraulicCosts.size(); k++){
                    if (i+j+k == (int) amountToProduce){
                        float total = windCosts.get(i) + solarCosts.get(j) + hydraulicCosts.get(k);
                        if (total < minVal){
                            minVal = total;
                            windPower.setAmount(i);
                            solarPower.setAmount(j);
                            hydraulicPower.setAmount(k);
                        }
                    }
                }
            }
        }

        return new MixResponse(windPower,solarPower,hydraulicPower,geothermalPower);


    }


    public MixResponse environmentalAlgorithm(SmartGridRequest smartGridRequest){

        float tempProd = 0;
        loadData(smartGridRequest);

        tempProd = repartionOfProduction(tempProd,amountToProduce,hydraulicPower, hydroPowerCapacity);
        tempProd = repartionOfProduction(tempProd,amountToProduce,windPower,windPowerCapacity);
        tempProd = repartionOfProduction(tempProd,amountToProduce,solarPower, solarPowerCapacity);
        tempProd = repartionOfProduction(tempProd,amountToProduce,geothermalPower, geothermalPowerCapacity);

        return new MixResponse(windPower,solarPower,hydraulicPower,geothermalPower);
    }

    ////////////////////////////// Helpers /////////////////////////////////////


    /**
     * This method permits to attribute an amount to produce for each production site
     * @param tempProd
     * @param amountToProduce
     * @param amountToProduceForASite
     * @param siteCurrentCapacity
     */
    private float repartionOfProduction(float tempProd, float amountToProduce, AmountToProduce amountToProduceForASite, float siteCurrentCapacity){
        if (tempProd<amountToProduce){
            if (siteCurrentCapacity + tempProd < amountToProduce){
                amountToProduceForASite.setAmount(siteCurrentCapacity);
                return tempProd + siteCurrentCapacity;
            }
            else {
                amountToProduceForASite.setAmount(amountToProduce - tempProd);
                return amountToProduce;
            }
        }
        return amountToProduce;
    }


    /**
     *
     * @param energyToProduce
     * @param energySitesCapacities
     * @param energyPower
     */
    private float[] percentageAlgoHelper(float tempProd, float energyToProduce, float energySitesCapacities, AmountToProduce energyPower){
        float[] result = new float[3];
        if (energySitesCapacities >= energyToProduce) {
            energyPower.setAmount(energyToProduce);
            //1- tempprod 2- energytoprduce 3- energysitecapacity
            result[0] = tempProd + energyToProduce;
            result[1] = 0;
            result[2] = energySitesCapacities - energyToProduce;

        } else {
            energyPower.setAmount(energySitesCapacities);
            result[0] = tempProd + energySitesCapacities;
            result[1] = energyToProduce - energySitesCapacities;
            result[2] = 0;
        }
        return result;
    }


    /**
     *
     * @param tempProd
     * @param amountToProduce
     * @param amountToProduceForASite
     * @param siteCurrentCapacity
     */
    private float percentageAlgoHelperBis(float tempProd, float amountToProduce, AmountToProduce amountToProduceForASite, float siteCurrentCapacity){
        if (tempProd<amountToProduce){
            if (siteCurrentCapacity + tempProd <= amountToProduce){
                amountToProduceForASite.setAmount(siteCurrentCapacity);
                return tempProd + siteCurrentCapacity;
            }
            else {
                amountToProduceForASite.setAmount((amountToProduce - tempProd) + amountToProduceForASite.getAmount());
                return amountToProduce ;
            }
        }
        return amountToProduce;
    }




    /**
     *
     * @param smartgridRequest
     */
    private void loadData(SmartGridRequest smartgridRequest){
        amountToProduce = smartgridRequest.getAmount();

        windPowerCapacity = smartgridRequest.getWindPowerSite().getEnergyProduction().getAmount();
        hydroPowerCapacity = smartgridRequest.getHydraulicPowerSite().getEnergyProduction().getAmount();
        solarPowerCapacity = smartgridRequest.getSolarPowerSite().getEnergyProduction().getAmount();
        geothermalPowerCapacity = smartgridRequest.getGeothermalPowerSite().getEnergyProduction().getAmount();

        windPower = new AmountToProduce(EnergyType.WIND);
        solarPower = new AmountToProduce(EnergyType.SOLAR);
        hydraulicPower = new AmountToProduce(EnergyType.HYDRAULIC);
        geothermalPower = new AmountToProduce(EnergyType.GEOTHERMAL);

    }

}
