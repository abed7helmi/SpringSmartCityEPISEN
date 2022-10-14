const url = "http://172.31.249.161:8080/energy-mix"




window.onload = () => {
    setInterval(retrievePositionTabData, 5000)
    setInterval(retrieveAlgoInUse, 5000)
    setInterval(retrievePercentageTabData, 5000)
}

const retrieveAlgoInUse = () => {
    fetch(url + "/algos-state")
        .then(response => response.json()).then(algosState => {

        let algoInUse = document.getElementById("algo-in-use")
        console.log(algosState)
        if (algosState.economicAlgoState == true){
            algoInUse.innerText = "Économique"
        } else if (algosState.defaultAlgoState == true){
            algoInUse.innerText = "Arbitraire"
        } else if (algosState.environmentAlgoState == true) {
            algoInUse.innerText = "Environnemental"
        } else {
            algoInUse.innerText = "Pourcentage"
        }

    }).catch(err => console.log(err.message))
}

const retrievePercentageTabData = () => {
    fetch(url + "/percentage-algo-parameters")
        .then(response => response.json()).then(percentageData => {
        let percentageTable = document.getElementById("energy-percentage")
        deleteAllRows(percentageTable)
        const energyPercentage = []
        energyPercentage.push({
            "energy" : "Énergie éolienne",
            "percentage" : percentageData.windPercentage
        })
        energyPercentage.push({
            "energy" : "Énergie solaire",
            "percentage" : percentageData.solarPercentage
        })
        energyPercentage.push({
            "energy" : "Énergie hydraulique",
            "percentage" : percentageData.hydraulicPercentage
        })
        energyPercentage.push({
            "energy" : "Énergie géothermique",
            "percentage" : percentageData.geothermalPercentage
        })

        energyPercentage.sort((a,b) => (a.percentage < b.percentage) ? 1 : ((b.percentage < a.percentage) ? -1 : 0))

        for (let i=0; i<energyPercentage.length; i++){
            let row = ` <tr>
                                        <td>${energyPercentage[i].energy} </td>
                                        <td>${energyPercentage[i].percentage} %</td>
                                   </tr> `
            percentageTable.innerHTML += row
        }

    }).catch(err => console.log(err.message))
}

const retrievePositionTabData = () => {
    fetch(url + "/default-algo-parameters")
        .then(response => response.json()).then(positionData => {

        let energyProritizationTable = document.getElementById("energy-prioritization")
        deleteAllRows(energyProritizationTable)
        const energyPrioritization = []
        energyPrioritization.push({
            "energy" : "Énergie éolienne",
            "position" : positionData.windPosition
        })
        energyPrioritization.push({
            "energy" : "Énergie solaire",
            "position" : positionData.solarPosition
        })
        energyPrioritization.push({
            "energy" : "Énergie hydraulique",
            "position" : positionData.hydraulicPosition
        })
        energyPrioritization.push({
            "energy" : "Énergie géothermique",
            "position" : positionData.geothermalPosition
        })

        energyPrioritization.sort((a,b) => (a.position > b.position) ? 1 : ((b.position > a.position) ? -1 : 0))

        for (let i=0; i<energyPrioritization.length; i++){
            let row = ` <tr>
                                        <td>${energyPrioritization[i].energy}</td>
                                        <td>${energyPrioritization[i].position}</td>
                                   </tr> `
            energyProritizationTable.innerHTML += row
        }

    }).catch(err => console.log(err.message))

}

retrievePositionTabData()
retrieveAlgoInUse()
retrievePercentageTabData()

const activatePercentageAlgo = () => {
    const percentageValues = document.getElementsByClassName("percentage-algo")
    const errorPercentage = document.getElementById("error-percentage")
    let sum = 0
    for (let i = 0; i<percentageValues.length; i++){
        if (parseInt(percentageValues[i].value) < 0){
            console.log(parseInt(percentageValues[i].value))
            swal("Veuillez insérer des valeurs positives","", "error");
            return;
        } else {
            sum += parseInt(percentageValues[i].value) || 0
        }
    }

    if(sum !== 100){
        console.log("vkkf,ve")
        console.log(sum)
        swal("La somme n'est pas égale à 100","", "error");
        //errorPercentage.style.display = 'block'
        return;
    }else{
        errorPercentage.style.display = 'none'
        const rec = {method : 'POST', headers: {}}
        for (let i = 0; i <4; i++){
            if (isNaN(percentageValues[i].value)){
                percentageValues[i].value = 0
            }
        }
        let windPercentage = parseInt(percentageValues[0].value)
        let solarPercentage = parseInt(percentageValues[1].value)
        console.log("fsfsfsd" + solarPercentage)
        console.log("fsfsfsd" + windPercentage)
        let hydraulicPercentage = parseInt(percentageValues[2].value)
        let geothermalPercentage = parseInt(percentageValues[3].value)
        if (isNaN(windPercentage)) windPercentage = 0
        if (isNaN(solarPercentage)) solarPercentage = 0
        if (isNaN(hydraulicPercentage)) hydraulicPercentage = 0
        if (isNaN(geothermalPercentage)) geothermalPercentage = 0
        fetch(url + "/activate-percentage-algo?wind="+windPercentage+"&solar="+solarPercentage+"&hydraulic="+ hydraulicPercentage + "&geothermal="+geothermalPercentage, rec)
        console.log("Algo percentage actif")
        $('#percentageAlgoModal').modal('hide');
        swal(" L'algorithme pourcentage a bien été activé","", "success");
    }
}
const energyList = ["Énergie éolienne", "Énergie solaire", "Énergie hydraulique", "Énergie géothermique"]

const priorityMap = {
    '0': 'wind',
    '1':'solar',
    '2':'hydraulic',
    '3':'geothermal'
}



const priority1 = document.getElementById("priority1")
const priority2 = document.getElementById("priority2")
const priority3 = document.getElementById("priority3")
const priority4 = document.getElementById("priority4")

function hasDuplicates(array) {
    return (new Set(array)).size !== array.length;
}

priority1.addEventListener("change", function(){
        flushOptions(priority2)
        flushOptions(priority3)
        flushOptions(priority4)
        for(x=0;x<this.options.length;x++){
            if(this.options[x].value != this.value && this.options[x].value != 4) {
                i = document.createElement("option");
                i.value = this.options[x].value;
                i.innerHTML = this.options[x].innerHTML;
                priority2.appendChild(i);
            }
        }

    }
);

priority2.addEventListener("change", function(){

        flushOptions(priority3)
        flushOptions(priority4)
        for(x=0;x<this.options.length;x++){
            if(this.options[x].value != this.value && this.options[x].value != 4) {
                i = document.createElement("option");
                i.value = this.options[x].value;
                i.innerHTML = this.options[x].innerHTML;
                priority3.appendChild(i);
            }
        }

    }
);

priority3.addEventListener("change", function(){
    flushOptions(priority4)
    //priority4.options.length=0;
    for(x=0;x<this.options.length;x++){
        if(this.options[x].value != this.value && this.options[x].value != 4) {
            i = document.createElement("option");
            i.value = this.options[x].value;
            i.innerHTML = this.options[x].innerHTML;
            priority4.appendChild(i);
        }
    }}
);

const activateEcoAlgo = () => {
    const rec = {method : 'POST', headers: {}}
    fetch(url + '/activate-economic-algo', rec).then()
    swal(" L'algorithme économique a bien été activé","", "success");
}

const activateEnvironmentalAlgo = () => {
    const rec = {method : 'POST', headers: {}}
    fetch(url + '/activate-environmental-algo', rec).then()
    swal(" L'algorithme environnemental a bien été activé","", "success");
}

const activateDefaultAlgo = () => {
    const defaultAlgoModal = document.getElementById("defaultAlgoModal")
    const priorityList = {
        'wind' : 1,
        'solar' : 2,
        'hydraulic' : 3,
        'geothermal' : 4
    }
    priorityList[priorityMap[priority1.value]]=1;
    priorityList[priorityMap[priority2.value]]=2;
    priorityList[priorityMap[priority3.value]]=3;
    priorityList[priorityMap[priority4.value]]=4;

    const rec = {method : 'POST', headers: {}}
    let tempList = []
    let canActivate = true
    tempList.push(priorityList.wind)
    tempList.push(priorityList.solar)
    tempList.push(priorityList.hydraulic)
    tempList.push(priorityList.geothermal)
    //hasDuplicates(tempList)
    console.log("Priority 1 " + priority1.value)
    console.log("Priority 2 " + priority2.value)
    console.log("Priority 3 " + priority3.value)
    console.log("Priority 4 " + priority4.value)
    if (priority1.value == 4 || priority2.value == 4 || priority3.value == 4 || priority4.value == 4) {
        swal("Veuillez sélectionner chaque type d'énergie !","", "error");
    }else{
        console.log(priorityList)
        fetch(url + "/activate-default-algo?wind=" + priorityList['wind'] + "&solar=" + priorityList['solar'] + "&hydraulic=" + priorityList['hydraulic'] + "&geothermal=" + priorityList['geothermal'], rec)
        $('#defaultAlgoModal').modal('hide');
        swal(" L'algorithme arbitraire a bien été activé","", "success");
    }

}



//////////////// Economic Line Chart ///////////////

var economicLineChart = document.getElementById("economic-line-chart")
var dataEconomicChart = {
    labels: [0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 180, 200],
    datasets: [{
        //data: data,
        function: function(x) { return 4 + Math.log(10 * x) },
        data: [],
        label: "Énergie éolienne",
        borderColor: "#8e5ea2",
        fill: false
    }, {
        //data: [168,170,178,190,203,276,408,547,675,734],
        function: function(x) { return 5 * Math.exp(-1/4 * x) + 8 },
        data: [],
        label: "Énergie solaire",
        borderColor: "#3cba9f",
        fill: false
    }, {
        //data: [40,20,10,16,24,38,74,167,508,784],
        function: function(x) { return -1/36 * x + 11 },
        data: [],
        label: "Énergie hydraulique",
        borderColor: "#e8c3b9",
        fill: false
    }, {
        //data: [6,3,2,2,7,26,82,172,312,433],
        function: function(x) { return 16 },
        data: [],
        label: "Énergie géothermique",
        borderColor: "#c45850",
        fill: false
    }
    ]
};
/*var eco = Chart.pluginService.register({
    beforeInit: function(chart) {
        var data = chart.config.data;
        for (var i = 0; i < data.datasets.length; i++) {
            for (var j = 0; j < data.labels.length; j++) {
                if (data.labels[j]==0 && data.datasets[i].label == "Énergie éolienne"){
                    x = data.labels[j]
                    y = 4
                } else {
                    var fct = data.datasets[i].function,
                        x = data.labels[j],
                        y = fct(x);
                }
                data.datasets[i].data.push(y);
            }
        }
        data.labels = [0, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1800, 2000]
    }
});*/

var economicChart = new Chart(economicLineChart, {
    plugins : [{
        beforeInit: function(chart) {
            var data = chart.config.data;
            for (var i = 0; i < data.datasets.length; i++) {
                for (var j = 0; j < data.labels.length; j++) {
                    if (data.labels[j]==0 && data.datasets[i].label == "Énergie éolienne"){
                        x = data.labels[j]
                        y = 4
                    } else {
                        var fct = data.datasets[i].function,
                            x = data.labels[j],
                            y = fct(x);
                    }
                    data.datasets[i].data.push(y);
                }
            }
            //data.labels = [0, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1800, 2000]
        }
    }],
    type: 'line',
    data: dataEconomicChart,
    options: {
        title: {
            display: true,
            text: 'Coût unitaire du kW pour chaque type d energie en fonction de la quantité produite'
        },
        scales: {
            yAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Cout unitaire (en centimes d euros)'
                },
                ticks: {
                    beginAtZero:true
                }
            }],
            xAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Quantité d énergie produite (en kW)'
                }
            }]
        }
    }
});

/////////////// End of economic Line Chart //////////

//////////////// Envionment Line Chart //////////////
var environmentalLineChart = document.getElementById("environmental-line-chart")
var dataEnvironmentChart = {
    labels: [0, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000],
    datasets: [{
        //data: data,
        function: function(x) { return 12 * x },
        data: [],
        label: "Énergie éolienne",
        borderColor: "#8e5ea2",
        fill: false
    }, {
        //data: [168,170,178,190,203,276,408,547,675,734],
        function: function(x) { return 41 * x },
        data: [],
        label: "Énergie solaire",
        borderColor: "#3cba9f",
        fill: false
    }, {
        //data: [40,20,10,16,24,38,74,167,508,784],
        function: function(x) { return 6 * x },
        data: [],
        label: "Énergie hydraulique",
        borderColor: "#e8c3b9",
        fill: false
    }, {
        //data: [6,3,2,2,7,26,82,172,312,433],
        function: function(x) { return 45 * x },
        data: [],
        label: "Énergie géothermique",
        borderColor: "#c45850",
        fill: false
    }
    ]
};
/*var env = Chart.pluginService.register({
    beforeInit: function(chart) {
        var data = chart.config.data;
        for (var i = 0; i < data.datasets.length; i++) {
            for (var j = 0; j < data.labels.length; j++) {
                var fct = data.datasets[i].function,
                    x = data.labels[j],
                    y = fct(x);
                data.datasets[i].data.push(y);
            }
        }
    }
});*/

var environmentalChart = new Chart(environmentalLineChart, {
    plugins : [{
        beforeInit: function(chart) {
            var data = chart.config.data;
            for (var i = 0; i < data.datasets.length; i++) {
                for (var j = 0; j < data.labels.length; j++) {
                    var fct = data.datasets[i].function,
                        x = data.labels[j],
                        y = fct(x);
                    data.datasets[i].data.push(y);
                }
            }
        }
    }],
    type: 'line',
    data: dataEnvironmentChart,
    options: {
        title: {
            display: true,
            text: 'Quantité de Co2 émise en fonction de la quantité d énergie produite (pour chaque type d énergie)'
        },
        scales: {
            yAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Quantité de Co2 rejetée (en grammes)'
                },
                ticks: {
                    beginAtZero:true
                }
            }],
            xAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Quantité d énergie produite (en kW)'
                }
            }]

        }
    }
});

////////////// End Environmental Line Chart /////////////////


function deleteAllRows(table) {
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
}

function flushOptions (priority){
    priority.options.length = 0
    i = document.createElement("option");
    i.value = 4;
    i.disabled = true;
    i.selected = true;
    i.innerHTML = "Sélectionnez";
    priority.appendChild(i);
}