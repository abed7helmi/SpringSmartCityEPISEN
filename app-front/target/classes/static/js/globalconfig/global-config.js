const ENV='INT'

const globals_local = {
    host : 'http://localhost',
    port : 8080
}

const globals_int = {
    host : 'http://172.31.249.161',
    port : 8080
}

const globals_pprod = {
    host : 'http://172.31.249.224',
    port : 8080
}

const globals_prod = {
    host : 'http://172.31.249.161',
    port : 8080
}

window.onload = () => {
    loadFeatureFlags(() => {
        loadParams()
    })
}

// Requests
    // Load Data
function loadParams() {
    sendRequest('GET', null, 'global-params', response => {
        setConfigsElement(response)
    })
}

function loadFeatureFlags(callback) {
    sendRequest('GET', null, 'feature-flags', response => {
        setFlagsElement(response, () => {
            callback()
        })
    })
}

    // Post Data
function updateParam(body) {
    sendRequest('POST', body, 'global-params/param', response => {
        if(response.status === "success") location.reload()
    })
}

function updateFeatureFlag(body) {
    sendRequest('POST', body, 'feature-flags/flag', response => {
        if(response.status !== "success") alert("Feature flag N'A PAS ETE mis à jour")
    })
}

// Elements
function setConfigsElement(configs) {
    const configTableElement = document.getElementById("config-table-values")

    configs.map(config => {
        let customId = `conf-${config.id}`
        configTableElement.innerHTML += `
            <td>${config.paramName}</td>
            <td>${config.paramValue}</td>
            <td>${getInput(customId, 'entrer une valeur')}</td>
        `
    })
}

function setFlagsElement(flags, callback) {
    const configTableElement = document.getElementById("config-table-values")
    let counter = 0;
    flags.map(flag => {
        let customId = `ff-${flag.id}`
        configTableElement.innerHTML += `
            <td>${flag.flagName}</td>
            <td colspan="2">${getToggle(customId, flag.flagValue)}</td>
        `
        counter++
        if(counter >= flags.length) {
            callback()
            return;
        }
    })
}

// Event hanlders
function onToggle(id, value) {
    const body = {
        'flag_id' : id,
        'flag_value' : value
    }
    updateFeatureFlag(body)
}

function updateParamEvent(id) {
    const paramValue = document.getElementById(`conf-${id}-input`).value
    if(paramValue == null || paramValue === "") alert("Aucune valeur saisie")
    else {
        const body = {
            'param_id' : id,
            'param_value' : paramValue
        }
        updateParam(body)
    }
}

// Requester
function sendRequest(method, body, path, callback) {
    let globals = getGlobals()

    let request = {
        method : `${method.toUpperCase()}`,
        headers : {
            'Content-Type' : 'application/json'
        }
    }
    if(body) request.body = JSON.stringify(body)

    fetch(`${globals.host}:${globals.port}/${path}`, request)
        .then(res => res.json())
        .then(res => {
            callback(res)
        })
        .catch(err => {console.log(err)})
}

//Helpers
function getGlobals() {
    switch (ENV) {
        case 'LOCAL' : return globals_local
        case 'INT' : return globals_int
        case 'PPROD' : return globals_pprod
        case 'PROD' : return globals_prod
    }
}

// Static element
function getToggle(id, isChecked) {
    let setChecked = isChecked ? 'checked' : ''
    return `
    <span>
        <label class="switch">
            <input type="checkbox" id=${id} ${setChecked} onchange="onToggle(${id.split('-')[1]}, checked)">
            <span class="slider round"></span>
        </label>
    </span>`
}

function getInput(id, value) {
    return `
        <input type="text" id="${id}-input" placeholder="${value}"">
        <input type="button" id="${id}" onclick="updateParamEvent(${id.split('-')[1]})" value="Modifier" ">
    `
}


