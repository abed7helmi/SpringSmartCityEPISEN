package com.pds.smartUs.BackEnd.appback.simulator.workers;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.ProductionSiteModel;
import com.pds.smartUs.BackEnd.appback.gloabalconfig.FeatureFlagsEnum;
import com.pds.smartUs.BackEnd.appback.services.globalconfigs.FeatureFlagService;
import com.pds.smartUs.BackEnd.appback.services.globalconfigs.GlobalParamsService;
import com.pds.smartUs.BackEnd.appback.services.smartgrid.CityEnergyService;
import com.pds.smartUs.BackEnd.appback.services.smartgrid.ProductionSiteService;
import com.pds.smartUs.BackEnd.appback.simulator.config.SmartGridVariables;
import com.pds.smartUs.BackEnd.appback.simulator.entities.*;
import com.pds.smartUs.BackEnd.appback.simulator.powerproduction.EnergyProductionRequest;
import com.pds.smartUs.BackEnd.appback.simulator.powersites.*;
import com.pds.smartUs.BackEnd.appback.smartgridmix.MixResponse;
import com.pds.smartUs.BackEnd.appback.smartgridmix.SmartGridRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SmartGrid implements ProductionSiteObservable, Runnable {

    private static final Logger logger = LoggerFactory.getLogger(SmartGrid.class);

    @Autowired
    private SmartGridResponse response;

    @Autowired
    private ProductionSiteService productionSiteService;

    @Autowired
    private FeatureFlagService featureFlagService;

    @Autowired
    private GlobalParamsService globalParamsService;

    @Autowired
    private CityEnergyService cityEnergyService;

    private SimulatorRequester simulatorRequester;

    private RealTimeCapacityGenerator capacityGenerator;

    private EnergyBalancer energyBalancer;

    private ProdConsGenerator energyGenerator;

    private List<SiteProductionResponse> productionResponse = new ArrayList<>();

    private double cityBalance;

    private long secondsElapsed = 0;

    private List<SmartGridRequest> mixRequest = new ArrayList<>();

    private long relatedTime = SmartGridVariables.SIMULATOR_REFRESH_FREQUENCY;


    public SmartGrid() {

    }

    public SmartGridResponse getSmartGridResponse() {
        return response;
    }

    @Override
    public void update(ProductionSite productionSite) {
        switch (productionSite.getEnergyType()) {
            case SOLAR      -> this.response.setSolarSite(productionSite);
            case WIND       -> this.response.setWindSite(productionSite);
            case HYDRAULIC  -> this.response.setHydraulicSite(productionSite);
            case GEOTHERMAL    -> this.response.setThermalSite(productionSite);
        }
    }

    @Override
    public void run() {
        simulatorRequester = new SimulatorRequester(globalParamsService);
        capacityGenerator = new RealTimeCapacityGenerator(productionSiteService, simulatorRequester);
        energyGenerator = new ProdConsGenerator(simulatorRequester, cityEnergyService, featureFlagService);
        energyBalancer = new EnergyBalancer(cityEnergyService);
        // Change thread name for the logger
        Thread.currentThread().setName("[SmartGrid-Thread] Main Thread");

        // launch simulator requester thread
        Thread requester = new Thread(simulatorRequester);
        requester.start();

        // launch consumptions/productions generation thread
        Thread consProdGenerator = new Thread(energyGenerator);
        consProdGenerator.start();

        // launch energy balancing thread
        Thread balancer = new Thread(energyBalancer);
        balancer.start();

        // launch energy balancing thread
        Thread generator = new Thread(capacityGenerator);
        generator.start();

        // Run Forrest Run
        while (true) {
            try {
                // Random wainting
                int wait = (int) (Math.random() * ((7000 - 2000) + 2000));
                // Get Building Balances
                this.cityBalance = energyBalancer.getEnergyBalance();

                // Get Sites Total Capacity
                if(cityBalance <= 0 && capacityGenerator.getSitesTotalCapacity() > Math.abs(cityBalance)) {
                    // Send request to the mix -- with feature flag
                    if(featureFlagService.getFlagStatusByName(FeatureFlagsEnum.MIX_REQUEST_FLAG)) {
                        MixResponse mixResponse = callMixEnergetic();
                        produceWithNoFossil(mixResponse);
                    }
                }
                else if(cityBalance <= 0) produceWithFossil();
                // Re-initialize consumptions and productions
                if(featureFlagService.getFlagStatusByName(FeatureFlagsEnum.DELETE_CONSPROD_FLAG)) {
                    cityEnergyService.deleteAllCurrentProductions();
                    cityEnergyService.deleteAllCurrentConsumptions();
                }
                if(productionResponse.size() > 20) productionResponse.remove(productionResponse.size() - 1);

                this.secondsElapsed++;
                Thread.sleep(7000 + wait);
            }
            catch (InterruptedException e) {
                logger.error(e.getLocalizedMessage());
            }
            catch (NullPointerException ignored) {}
        }

    }

    // middlewares
    public void generateCapacities() {}

    // setter getters
    public List<SmartGridRequest> getMixRequest() {
        return mixRequest;
    }

    public List<SiteCapacitiesResponse> getSiteCapacities() {
        return capacityGenerator.getSiteCapacities();
    }

    public SimulatorResponse getSimulatorResponse() {
        return simulatorRequester.getSimulatorResponse();
    }

    public long getRelatedTime() {
        return relatedTime;
    }

    public void setRelatedTimeInSeconds(long relatedTime) {
        this.relatedTime = relatedTime;
    }

    public void setRelatedTimeInMinutes(long relatedTime) {
        this.relatedTime = relatedTime * 60;
    }

    public double getCityBalance() {
        return energyBalancer.getEnergyBalance();
    }

    public CityBalanceResponse getCityBalances() {
        return energyBalancer.getBalanceResponse();
    }

    public List<SiteProductionResponse> getProductionResponse() {
        return productionResponse;
    }

    // Workers
    public MixResponse callMixEnergetic() {
        this.cityBalance = energyBalancer.getEnergyBalance();

        WindPowerSite windPowerSite = new WindPowerSite();
        GeothermalPowerSite geothermalPowerSite = new GeothermalPowerSite();
        SolarPowerSite solarPowerSite = new SolarPowerSite();
        HydraulicPowerSite hydraulicPowerSite = new HydraulicPowerSite();

        calculateEnergiesToProduce(windPowerSite, geothermalPowerSite, solarPowerSite, hydraulicPowerSite);

        float totalProduction = windPowerSite.getEnergyProduction().getAmount()
                + solarPowerSite.getEnergyProduction().getAmount()
                + hydraulicPowerSite.getEnergyProduction().getAmount()
                + geothermalPowerSite.getEnergyProduction().getAmount();

        SmartGridRequest smartgridRequest = new SmartGridRequest(-1 * ((float) cityBalance),
                windPowerSite, solarPowerSite, hydraulicPowerSite, geothermalPowerSite,
                simulatorRequester.getSimulatorResponse().getSimulatorTime().toString());

        MixResponse response = new MixResponse();

        if(cityBalance < 0 && Math.abs(cityBalance) <= totalProduction){
            try{
                response = new RestTemplate().postForObject(
                        "http://localhost:8080/energy-mix/mix-infos",
                        smartgridRequest,
                        MixResponse.class);

                if(mixRequest.size() >= 20) mixRequest.remove(mixRequest.size() - 1);
                mixRequest.add(0, smartgridRequest);

            }catch (ResourceAccessException error) {
                logger.error("Request to mix energetic could not be performed!");
            }
        }
        return response;
    }

    public void produceWithFossil(){
        this.cityBalance = energyBalancer.getEnergyBalance();

        List<SiteCapacitiesResponse> siteCapacities = getSiteCapacities();
        // Take all the renewable energy
        siteCapacities.forEach(siteCapacity -> {

            SiteProductionResponse prodResponse = new SiteProductionResponse();
            prodResponse.setTime(getSimulatorResponse().getSimulatorTime().toString());
            prodResponse.setProductionSite(siteCapacity.getProductionSite());
            prodResponse.setAmount(siteCapacity.getCapacity());

            subtractCityBalance(siteCapacity.getCapacity());
            energyBalancer.setEnergyBalance(cityBalance);
            prodResponse.setCityBalance(cityBalance);

            if(siteCapacity.getCapacity() > 0) addProductionResponse(prodResponse);
        });

        // Take the rest of the need from the non-renewable sites
        List<ProductionSiteModel> fossilSites = productionSiteService.getFossilProductionSites();
        fossilSites.forEach(site -> {
            if(Math.abs(this.cityBalance) > 0) {
                SiteProductionResponse prodResponse = new SiteProductionResponse();
                prodResponse.setTime(getSimulatorResponse().getSimulatorTime().toString());
                prodResponse.setProductionSite(site);
                double amount = Math.abs(this.cityBalance);
                if(amount > site.getMaxProductionCapacity()) amount = site.getMaxProductionCapacity();
                prodResponse.setAmount(amount);
                subtractCityBalance(amount);
                energyBalancer.setEnergyBalance(cityBalance);
                prodResponse.setCityBalance(cityBalance);
                if (amount > 0) addProductionResponse(prodResponse);
            }
        });
        if(productionResponse.size() > 0) addProductionResponse(new SiteProductionResponse());
    }

    public void produceWithNoFossil(MixResponse mixResponse) {
        this.cityBalance = energyBalancer.getEnergyBalance();

        List<SiteCapacitiesResponse> siteCapacities = getSiteCapacities();

        double solarAmount = mixResponse.getSolarEnergyAmount().getAmount();
        double windAmount = mixResponse.getWindEnergyAmount().getAmount();
        double geothermalAmount = mixResponse.getGeothermalEnergyAmount().getAmount();
        double hydraulicAmount = mixResponse.getHydraulicEnergyAmount().getAmount();

        // Take all the renewable energy
        for(SiteCapacitiesResponse siteCapacity : siteCapacities) {
            SiteProductionResponse prodResponse = new SiteProductionResponse();
            prodResponse.setProductionSite(siteCapacity.getProductionSite());
            prodResponse.setTime(getSimulatorResponse().getSimulatorTime().toString());

            double maxCapacity = siteCapacity.getProductionSite().getMaxProductionCapacity();

            switch (siteCapacity.getProductionSite().getEnergyCaracs().getEnergytype()) {
                case SOLAR -> {
                    if(solarAmount > 0) {
                        double amount = solarAmount;
                        if(solarAmount >= maxCapacity) amount = maxCapacity;
                        prodResponse.setAmount(amount);
                        subtractCityBalance(amount);
                        mixResponse.getSolarEnergyAmount().setAmount((float) (solarAmount - amount));
                        prodResponse.setCityBalance(cityBalance);
                        addProductionResponse(prodResponse);
                        solarAmount -= amount;
                    }
                }
                case WIND -> {
                    if(windAmount > 0) {
                        double amount = windAmount;
                        if(windAmount >= maxCapacity) amount = maxCapacity;
                        prodResponse.setAmount(amount);
                        subtractCityBalance(amount);
                        mixResponse.getWindEnergyAmount().setAmount((float) (windAmount - amount));
                        productionResponse.add(prodResponse);
                        prodResponse.setCityBalance(cityBalance);
                        addProductionResponse(prodResponse);
                        windAmount -= amount;
                    }
                }
                case HYDRAULIC -> {
                    if(hydraulicAmount > 0) {
                        double amount = hydraulicAmount;
                        if(hydraulicAmount >= maxCapacity) amount = maxCapacity;
                        prodResponse.setAmount(amount);
                        subtractCityBalance(amount);
                        mixResponse.getHydraulicEnergyAmount().setAmount((float) (hydraulicAmount - amount));
                        productionResponse.add(prodResponse);
                        prodResponse.setCityBalance(cityBalance);
                        addProductionResponse(prodResponse);
                        hydraulicAmount -= amount;
                    }
                }
                case THERMAL -> {
                    if(geothermalAmount > 0) {
                        double amount = geothermalAmount;
                        if(geothermalAmount >= maxCapacity) amount = maxCapacity;
                        prodResponse.setAmount(amount);
                        subtractCityBalance(amount);
                        mixResponse.getGeothermalEnergyAmount().setAmount((float) (geothermalAmount - amount));
                        prodResponse.setCityBalance(cityBalance);
                        addProductionResponse(prodResponse);
                        geothermalAmount -= amount;
                    }
                }
            }
            energyBalancer.setEnergyBalance(cityBalance);
        }
        if(productionResponse.size() > 0) addProductionResponse(new SiteProductionResponse());
    }

    // Reversibility with Mehdi's scope -- To Be Deleted
    public void calculateEnergiesToProduce(WindPowerSite windSite, GeothermalPowerSite geoThermalSite,
                                           SolarPowerSite solarSite, HydraulicPowerSite hydraulicSite) {

        AtomicInteger wind = new AtomicInteger(1);
        AtomicInteger solar = new AtomicInteger(1);
        AtomicInteger hydraulic = new AtomicInteger(1);
        AtomicInteger geothermal = new AtomicInteger(1);

        this.getSiteCapacities().forEach(site -> {
            switch (site.getProductionSite().getEnergyCaracs().getEnergytype()) {
                case SOLAR -> {
                    if(site.getCapacity() > 0) {
                        EnergyProductionRequest request = new EnergyProductionRequest();
                        request.setAmountToProduce((float) (site.getCapacity()));
                        solarSite.produce(request);
                        solar.getAndIncrement();
                    }
                }
                case WIND -> {
                    if(site.getCapacity() > 0) {
                        EnergyProductionRequest request = new EnergyProductionRequest();
                        request.setAmountToProduce((float) (site.getCapacity()));
                        windSite.produce(request);
                        wind.getAndIncrement();
                    }
                }
                case HYDRAULIC -> {
                    if(site.getCapacity() > 0) {
                        EnergyProductionRequest request = new EnergyProductionRequest();
                        request.setAmountToProduce((float) (site.getCapacity()));
                        hydraulicSite.produce(request);
                        hydraulic.getAndIncrement();
                    }
                }
                case GEOTHERMAL -> {
                    if(site.getCapacity() > 0) {
                        EnergyProductionRequest request = new EnergyProductionRequest();
                        request.setAmountToProduce((float) (site.getCapacity()));
                        geoThermalSite.produce(request);
                        geothermal.getAndIncrement();
                    }
                }
            }
        });
    }

    // Helpers
    private void subtractCityBalance(double value) {
        if(cityBalance <= 0) cityBalance += value;
        // double mult = 1;
        // if(cityBalance <= 0) mult = -1;
        // cityBalance = mult * (Math.abs(cityBalance) - value);
        //cityBalance += value;
    }

    private void addProductionResponse(SiteProductionResponse res){
        productionResponse.add(0, res);
        if(productionResponse.size() > 30) productionResponse.remove(productionResponse.size() - 1);
    }

}
