var windPdAmountElement           = null
var windPdMaxElement              = null
var windPdCurrentElement          = null
var windPdDelayElement            = null

var solarPdAmountElement          = null
var solarPdMaxElement             = null
var solarPdCurrentElement         = null
var solarPdDelayElement           = null

var hydraulicPdAmountElement      = null
var hydraulicPdMaxElement         = null
var hydraulicPdCurrentElement     = null
var hydraulicPdDelayElement       = null

var geothermalPdAmountElement     = null
var geothermalPdMaxElement        = null
var geothermalPdCurrentElement    = null
var geothermalPdDelayElement      = null

window.onload = () => {
    __init()
    setInterval(() => {
        getParams()
    }, 1000)
}


const __init = () => {
    // Param init
    windPdAmountElement           = document.getElementById("wind-pd-amount");
    windPdMaxElement              = document.getElementById("wind-pd-max");
    windPdCurrentElement          = document.getElementById("wind-pd-capacity");
    windPdDelayElement            = document.getElementById("wind-pd-delay");

    solarPdAmountElement          = document.getElementById("solar-pd-amount");
    solarPdMaxElement             = document.getElementById("solar-pd-max");
    solarPdCurrentElement         = document.getElementById("solar-pd-capacity");
    solarPdDelayElement           = document.getElementById("solar-pd-delay");

    hydraulicPdAmountElement      = document.getElementById("hydraulic-pd-amount");
    hydraulicPdMaxElement         = document.getElementById("hydraulic-pd-max");
    hydraulicPdCurrentElement     = document.getElementById("hydraulic-pd-capacity");
    hydraulicPdDelayElement       = document.getElementById("hydraulic-pd-delay");

    geothermalPdAmountElement     = document.getElementById("geothermal-pd-amount");
    geothermalPdMaxElement        = document.getElementById("geothermal-pd-max");
    geothermalPdCurrentElement    = document.getElementById("geothermal-pd-capacity");
    geothermalPdDelayElement      = document.getElementById("geothermal-pd-delay");
}

const getParams = () => {
    fetch("http://172.31.249.161:8080/smartgrid/sites/productions")
        .then(response => response.json())
        .then(productionData => {
            if(productionData) {
                console.log(productionData)
                windPdAmountElement.innerText        = productionData.windSite.energyProduction.amount;
                windPdMaxElement.innerText           = productionData.windSite.maxCapacity;
                windPdCurrentElement.innerText       = productionData.windSite.currentCapacity;
                windPdDelayElement.innerText         = productionData.windSite.site_delay;

                solarPdAmountElement.innerText       = productionData.solarSite.energyProduction.amount;
                solarPdMaxElement.innerText          = productionData.solarSite.maxCapacity;
                solarPdCurrentElement.innerText      = productionData.solarSite.currentCapacity;
                solarPdDelayElement.innerText        = productionData.solarSite.site_delay;

                hydraulicPdAmountElement.innerText   = productionData.hydraulicSite.energyProduction.amount;
                hydraulicPdMaxElement.innerText      = productionData.hydraulicSite.maxCapacity;
                hydraulicPdCurrentElement.innerText  = productionData.hydraulicSite.currentCapacity;
                hydraulicPdDelayElement.innerText    = productionData.hydraulicSite.site_delay;

                geothermalPdAmountElement.innerText  = productionData.thermalSite.energyProduction.amount;
                geothermalPdMaxElement.innerText     = productionData.thermalSite.maxCapacity;
                geothermalPdCurrentElement.innerText = productionData.thermalSite.currentCapacity;
                geothermalPdDelayElement.innerText   = productionData.thermalSite.site_delay;
            }
        })
        .catch(error => console.log(error.message))
}

