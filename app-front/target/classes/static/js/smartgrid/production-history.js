let url = "http://172.31.249.161:8080"

//////////////// Envionment Line Chart //////////////
var environmentalLineChart = document.getElementById("environmental-line-chart")
var dataEnvironmentChart = {
    labels: ["janvier", "février", "mars", "avril", "mai", "juin", "juillet", "aout", "septembre", "octobre", "novembre", "décembre"],
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
        data: [4],
        label: "Énergie solaire",
        borderColor: "#3cba9f",
        fill: false
    }, {
        //data: [40,20,10,16,24,38,74,167,508,784],
        function: function(x) { return 6 * x },
        data: [4, 4],
        label: "Énergie hydraulique",
        borderColor: "#e8c3b9",
        fill: false
    }, {
        //data: [6,3,2,2,7,26,82,172,312,433],
        function: function(x) { return 45 * x },
        data: [4],
        label: "Énergie géothermique",
        borderColor: "#c45850",
        fill: false
    }
    ]
};


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
            text: 'Quantité d energie renouvelable produite en 2021 (pour chaque type d énergie)'
        },
        scales: {
            yAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Quantité d énergie produite (en kW)'
                },
                ticks: {
                    beginAtZero:true
                }
            }],
            xAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: ''
                }
            }]

        }
    }
});


const retrieveChartData = () => {
    fetch(url + "/wind-history")
        .then(response => response.json()).then(windHistory => {
        console.log(windHistory)
        dataEnvironmentChart.datasets[0].data = [windHistory[4][0],windHistory[3][0],windHistory[7][0], windHistory[0][0], windHistory[8][0], windHistory[6][0], windHistory[5][0], windHistory[1][0], windHistory[11][0], windHistory[10][0], windHistory[9][0], windHistory[2][0]]
        refreshData()
    }).catch(err => console.log(err.message))

    fetch(url + "/solar-history")
        .then(response => response.json()).then(solarHistory => {
        console.log(solarHistory)
        dataEnvironmentChart.datasets[1].data = [solarHistory[4][0],solarHistory[3][0],solarHistory[7][0], solarHistory[0][0], solarHistory[8][0], solarHistory[6][0], solarHistory[5][0], solarHistory[1][0], solarHistory[11][0], solarHistory[10][0], solarHistory[9][0], solarHistory[2][0]]
        refreshData()
    }).catch(err => console.log(err.message))
    fetch(url + "/hydraulic-history")
        .then(response => response.json()).then(hydraulicHistory => {
        console.log(hydraulicHistory)
        dataEnvironmentChart.datasets[2].data = [hydraulicHistory[4][0],hydraulicHistory[3][0],hydraulicHistory[7][0], hydraulicHistory[0][0], hydraulicHistory[8][0], hydraulicHistory[6][0], hydraulicHistory[5][0], hydraulicHistory[1][0], hydraulicHistory[11][0], hydraulicHistory[10][0], hydraulicHistory[9][0], hydraulicHistory[2][0]]
        refreshData()
    }).catch(err => console.log(err.message))
    fetch(url + "/thermal-history")
        .then(response => response.json()).then(thermalHistory => {
        console.log(thermalHistory)
        dataEnvironmentChart.datasets[3].data = [thermalHistory[4][0],thermalHistory[3][0],thermalHistory[7][0], thermalHistory[0][0], thermalHistory[8][0], thermalHistory[6][0], thermalHistory[5][0], thermalHistory[1][0], thermalHistory[11][0], thermalHistory[10][0], thermalHistory[9][0], thermalHistory[2][0]]
        refreshData()
    }).catch(err => console.log(err.message))
    refreshData()
}


const refreshData = () => {
    environmentalChart.update()
}


window.onload = () => {
    setInterval(()=> {retrieveChartData()}, 5000)
}

retrieveChartData()