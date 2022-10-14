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

const expands = {}

function loadData() {
    getEcoDistrictsBalance()
}


function updatePage() {
    loadData()
    setInterval(()=> {
        loadData()
    }, 3000)
}

// Requesters
function getEcoDistrictsBalance() {
    sendRequest('GET', false, 'city/balances', response => {
        if(response.districts) updateEcoDistrictsElement(response.districts)
    })
}

// Updaters
function updateEcoDistrictsElement(districts) {
    let listDistrictsElement = document.getElementById("list-eco-districts");
    listDistrictsElement.innerHTML = ""

    districts.map(district => {
        let districtId = district.district.id
        listDistrictsElement.innerHTML += getDistrictItem(districtId, district.district.districtName,
            district.amountConsumed.toFixed(2), district.amountProduced.toFixed(2))

        listDistrictsElement.innerHTML += getDistrictHabitatItem(districtId, district.buildings, district.houses)

        //document.getElementById(`district-${districtId}-expand`).onclick = expand("habitat-"+districtId)
    })
}

// Event Listeners
function expand(id) {
    let item = document.getElementById(id)
    let expandItem = document.getElementById(`district-${id.split('-')[1]}-expand`)

    let type = "none"
    let exMsg = "Afficher"
    if(item.style.display === "none") {
        type = "grid"
        exMsg = "Masquer"
    }
    expandItem.innerText = exMsg
    item.style.display = type
    expands[`expand-${id.split('-')[1]}`] = type
}

// Raw Elements
function getDistrictItem(id, name, totalCons, totalProd) {
    return `
        <section class="eco-district-item">
            <span class="eco-district-name">${name.toUpperCase()}</span>
            <span class="eco-district-info">
                <span class="eco-district-consumption" style="color: #f1004d">
                    Total Consomation : <span>${totalCons}</span> kwH
                </span>
                <div class="horizontal-sep"></div>
                <span class="eco-district-production" style="color: #2bd918">
                    Total Production : <span>${totalProd}</span> kwH
                </span>
                <div class="horizontal-sep"></div>
                <span class="eco-district-balance">
                    Solde : <span id="eco-district-solde">${totalProd - totalCons}</span> kwH
                    </span>
                <div class="horizontal-sep"></div>
                <span id="district-${id}-expand" onclick="expand('habitat-${id}')" style="cursor: pointer">
                    ${ (expands[`expand-${id}`] === "none" || !expands[`expand-${id}`]) ? "Afficher" : "Masquer"}
                </span>
            </span>
        </section>
    `
}

function getDistrictHabitatItem(id, buildings, houses) {
    if(!expands[`expand-${id}`]) {
        expands[`expand-${id}`] = "none"
    }
    return `
        <section class="eco-district-habitats" id="habitat-${id}" style="display: ${expands[`expand-${id}`]}">
            <div style="margin-bottom: 15px">
                <span>Bâtiments</span>
            </div>
            <div>
                <table class="sg-dashboard-table">
                    <thead>
                    <tr>
                        <th>Nom Bâtiment</th>
                        <th>Nombre d'appartements</th>
                        <th>Nombre d'habitants</th>
                        <th>Nombre d'étages</th>
                        <th>Consommation Actuelle</th>
                        <th>Production Actuelle</th>
                    </tr>
                    </thead>
                    <tbody class="disctrict-buildings">
                        ${getHabitatBuildings(buildings)}
                    </tbody>
                </table>
            </div>

            <div style="margin: 15px 0 15px 0">
                <span>Maisons</span>
            </div>
            <div>
                <table class="sg-dashboard-table">
                    <thead>
                    <tr>
                        <th>Adresse</th>
                        <th>Nombre d'unités</th>
                        <th>Nombre d'habitants</th>
                        <th>Consommation Actuelle</th>
                    </tr>
                    </thead>
                    <tbody id="district-houses">
                        ${getHabitatHouses(houses)}
                    </tbody>
                </table>
            </div>
        </section>
    `
}

function getHabitatBuildings(buildings) {
    let element = ""
    buildings.map(building => {
        element += `
            <tr>
                <td>${building["building"]["buildingName"].toUpperCase()}</td>
                <td>${building["building"]["nbApartment"]}</td>
                <td>${building["building"]["nbTenants"]}</td>
                <td>${building["building"]["nbFloors"]}</td>
                <td>${building["amountConsumed"].toFixed(2)}</td>
                <td>${building["amountProduced"].toFixed(2)}</td>
            </tr>
        `
    })
    return element
}

function getHabitatHouses(houses) {
    let element = ""
    houses.map(house => {
        element += `
            <tr>
                <td>${house["house"]["address"]}</td>
                <td>${house["house"]["nbUnits"]}</td>
                <td>${house["house"]["nbTenants"]}</td>
                <td>${house["amountConsumed"].toFixed(2)}</td>
            </tr>
        `
    })
    return element
}

// Helpers
function sendRequest(method, body, path, callback) {
    let request = {
        method : `${method.toUpperCase()}`,
        headers : {
            'Content-Type' : 'application/json'
        }
    }
    if(body) request.body = JSON.stringify(body)

    const globals = getGlobals()

    fetch(`${globals.host}:${globals.port}/${globals.endpoint}/${path}`, request)
        .then(res => res.json())
        .then(res => {
            callback(res)
        })
        .catch(err => {
            // if connection Refused (back not running)
            console.log(err)
        })
}

function getGlobals() {
    switch (ENV) {
        case 'LOCAL' : return globals_local
        case 'INT' : return globals_int
        case 'PPROD' : return globals_pprod
        case 'PROD' : return globals_prod
    }
}