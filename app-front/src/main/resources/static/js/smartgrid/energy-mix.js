var url = "http://172.31.249.161:8080/energy-mix"

var data = [12,122,0,0]

const chart = new Chart(document.getElementById("pie-chart"), {
    type: 'pie',
    data: {
        labels: ["Énergie éolienne", "Énergie solaire", "Énergie hydraulique", "Énergie géothermique"],
        datasets: [{
            label: "",
            backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850"],
            data: data
        }]
    },
    options: {
        title: {
            display: false,
            text: 'Répartition de la production (en kW)'
        }
    }
});

const refreshData = () => {
    chart.update()
}

function deleteAllRows(table) {
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
}

window.onload = () => {
    setInterval(retrieveChartData, 5000)
    setInterval(retrieveLatestMixResponses, 5000)
}

const retrieveChartData = () => {
    fetch(url + "/mix-result")
        .then(response => response.json()).then(dataMix => {
        console.log(dataMix)
        let dateElement = document.getElementById("dateMix")
        let amountElement = document.getElementById("amount")
        chart.data.datasets[0].data[0]=Math.trunc(dataMix.windAmount)
        chart.data.datasets[0].data[1]=Math.trunc(dataMix.solarAmount)
        chart.data.datasets[0].data[2]=Math.trunc(dataMix.hydraulicAmount)
        chart.data.datasets[0].data[3]=Math.trunc(dataMix.geothermalAmount)
        dateElement.innerText = dataMix.date
        amountElement.innerText = Math.trunc(dataMix.amountToProduce)
        refreshData()
    }).catch(err => console.log(err.message))
}

const retrieveLatestMixResponses = () => {
    fetch(url + "/latest-mix-result")
        .then(response => response.json()).then(latestMixResult => {
        const smartgridRequests = document.getElementById("sg-requests-table")
        deleteAllRows(smartgridRequests)
        for (var i=0; i<latestMixResult.length; i++){
            console.log("Amout To PRoducie" + latestMixResult[i].amountToProduce)
            var row = ` <tr>
                                <td class="text-center">${Math.trunc(latestMixResult[i].amountToProduce)}</td>
                                <td class="text-center">${latestMixResult[i].date}</td>
                                <td class="text-center">${Math.trunc(latestMixResult[i].windAmount)}</td>
                                <td class="text-center">${Math.trunc(latestMixResult[i].solarAmount)}</td>
                                <td class="text-center">${Math.trunc(latestMixResult[i].hydraulicAmount)}</td>
                                <td class="text-center">${Math.trunc(latestMixResult[i].geothermalAmount)}</td>
                           </tr> `
            smartgridRequests.innerHTML += row
        }

        console.log(latestMixResult)
    }).catch(err => console.log(err.message))
}


retrieveChartData()
retrieveLatestMixResponses()




