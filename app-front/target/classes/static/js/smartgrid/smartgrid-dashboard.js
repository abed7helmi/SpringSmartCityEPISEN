const ENV='INT'

const globals_local = {
    host : 'http://localhost',
    port : 8080,
    endpoint : 'smartgrid'
}

const globals_int = {
    host : 'http://172.31.249.161',
    port : 8080,
    endpoint : 'smartgrid'
}

const globals_pprod = {
    host : 'http://172.31.249.224',
    port : 8080,
    endpoint : 'smartgrid'
}

const globals_prod = {
    host : 'http://172.31.249.161',
    port : 8080,
    endpoint : 'smartgrid'
}

let loadingFrequency = 1
let interval

function loadData() {
    //getFrequency()
    getCityBalance()
    getSitesCapacities()
    getSimulatorParams()
    getMixRequests()
    getSitesProductions()
}

function updatePage() {
    interval = setInterval(()=> {
        loadData()
    }, 1000 * loadingFrequency)
}

function updatePageOnce() {
    loadData()
}

// Requesters
//gettes
function getFrequency() {
    sendRequest('GET', false, 'config/frequency-time', response => {
        updateFrequencyValueElement(response)
    })
}

function getSitesCapacities() {
    sendRequest('GET', false, 'sites/capacities', response => {
        updateCapacitiesElement(response)
    })
}

function getSitesProductions() {
    sendRequest('GET', false, 'sites/productions', response => {
        updateProductionElement(response)
    })
}

function getCityBalance() {
    sendRequest('GET', false, 'energy-balance', response => {
        if(response.status === "success") {
            updateCityBalanceElement(response)
        }
    })
}

function getSimulatorParams() {
    sendRequest('GET', false, 'mock-parameters', response => {
        updateMockParamsElement(response)
    })
}

function getMixRequests() {
    sendRequest('GET', false, 'mix-requests', response => {
        updateMixRequestsElement(response)
    })
}
//senders
function setFrequency(frequencyValue) {
    let body = {
        "frequency" : frequencyValue,
    }
    sendRequest('POST', body, 'frequency', response => {
        if(response.code === "success") {
            updateFrequencyValueElement(frequencyValue)
        }
    }, true)
}

function setLoadingFrequency(freq) {
    freq = Number.parseInt(freq)
    clearInterval(interval)
    if(freq !== 0) interval = setInterval(() => {loadData()}, 1000 * freq)
}


// Update Elements
function updateFrequencyValueElement(frequency) {
    const currentFrequencyElement = document.getElementById("sg-frequency");

    currentFrequencyElement.innerText = frequency
}

function updateCityBalanceElement(response) {
    const balanceElement = document.getElementById("sg-city-solde");
    const timeUpdateElement = document.getElementById("balance-last-update");

    let balance = parseFloat(response['city-balance']).toFixed(2)
    let updateTime = response['update-time']

    if(balance < 0) {
        balanceElement.style.color = 'rgba(204,38,38,0.89)'
    } else {
        balanceElement.style.color = 'rgba(38,134,41,0.89)'
    }
    balanceElement.innerText = `${balance} kWh`
    timeUpdateElement.innerText = updateTime
}

function updateCapacitiesElement(response) {
    const capacitiesTableElement = document.getElementById("capacities-table-body");
    capacitiesTableElement.innerHTML = ''
    response.map(site => {
        let energyType = site["production-site"]["energyCaracs"]["energytype"]
        let imgElement = getIconFromEnergyType(energyType)
        capacitiesTableElement.innerHTML +=
            `<tr>
                <td style="text-align: left; padding-left: 15px;">${site["production-site"]["siteName"]}</td>
                <td>${energyType}</td>
                <td>${imgElement}</td>
                <td>${site["production-site"]["maxProductionCapacity"]}</td>
                <td class="real-time-capacity">${site["production-capacity"].toFixed(2)}</td>
            </tr>
            `
    })
}

function updateProductionElement(response) {
    const productionsTableElement = document.getElementById("productions-table-body");
    productionsTableElement.innerHTML = ''
    response.map(site => {
        if(!site["production-site"]) {
            productionsTableElement.innerHTML += `<tr style="height: 3px; background: rgba(255,255,255,0.5);"><td colspan="7"></td></tr>`
            return;
        }
        let energyType = site["production-site"]["energyCaracs"]["energytype"]
        let imgElement = getIconFromEnergyType(energyType)
        productionsTableElement.innerHTML +=
            `<tr>
                <td style="text-align: left; padding-left: 15px;">${site["production-site"]["siteName"]}</td>
                <td>${energyType}</td>
                <td>${imgElement}</td>
                <td>${site["production-site"]["maxProductionCapacity"]}</td>
                <td style="font-size: 15px" class="real-time-production">${site["production-amount"].toFixed(2)}</td>
                <td>${site["production-time"]}</td>
                <td style="font-size: 15px">${site["city-balance"].toFixed(2)}</td>
            </tr>
            `
    })
}

function updateMockParamsElement(response) {
    const tempParamElement = document.getElementById("temp-param");
    const windParamElement = document.getElementById("wind-param");
    const precipitationParamElement = document.getElementById("precipitations-param");
    const cloudsParamElement = document.getElementById("clouds-param");
    const timeParamElement = document.getElementById("time-param");

    timeParamElement.innerText = response.time.hours + 'h' + response.time.minutes + 'm'
    tempParamElement.innerText = response.params.temperature + ' °C'
    windParamElement.innerText = response.params.wind + ' kmh'
    cloudsParamElement.innerText = response.params.clouds + ' %'
    precipitationParamElement.innerText = response.params.precipitations + ' %'

}

function updateMixRequestsElement(response) {
    const mixRequestsTableElement = document.getElementById("mix-requests-table-body");

    mixRequestsTableElement.innerHTML = ''

    response.map(request => {
        mixRequestsTableElement.innerHTML +=
            `<tr>
                <td>${request["solar-sites"]["energyProduction"]["amount"]}</td>
                <td>${request["wind-sites"]["energyProduction"]["amount"]}</td>
                <td>${request["hydraulic-sites"]["energyProduction"]["amount"]}</td>
                <td>${request["geothermal-sites"]["energyProduction"]["amount"]}</td>
                <td>${request["amount"]}</td>
                <td>${request["request-time"]}</td>
            </tr>
            `
    })
}

// Helpers
function sendRequest(method, body, path, callback, ...toSimulator) {
    let request = {
        method : `${method.toUpperCase()}`,
        headers : {
            'Content-Type' : 'application/json'
        }
    }
    if(body) request.body = JSON.stringify(body)

    const globals = getGlobals()

    let port = globals.port
    let endpoint = globals.endpoint

    if(toSimulator.length > 0) {
        port = '4040'
        endpoint = 'v2/simulator'
    }

    fetch(`${globals.host}:${port}/${endpoint}/${path}`, request)
        .then(res => res.json())
        .then(res => {
            callback(res)
        })
        .catch(err => {
            // if connection Refused (back not running)
            console.log(err)
        })
}

function getIconFromEnergyType(type) {
    let imgType = ''
    switch (type) {
        case "SOLAR" : imgType = "sun"
            break
        case "HYDRAULIC" : imgType = "hydraulic"
            break
        case "WIND" : imgType = "wind"
            break
        case "THERMAL" : imgType = "geo"
            break
        case "FOSSIL" : imgType = "fossil"
            break
    }
    return `<img src="/smartgridassets/${imgType}.png" style="width: 18px; height: 18px;">`
}

function getGlobals() {
    switch (ENV) {
        case 'LOCAL' : return globals_local
        case 'INT' : return globals_int
        case 'PPROD' : return globals_pprod
        case 'PROD' : return globals_prod
    }
}