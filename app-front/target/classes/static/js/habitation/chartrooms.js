import{connexionBack2} from "./habitation.js"
$(document).ready(function () {

    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const eq = urlParams.get('roomid')
    var urld=connexionBack2+'consomptionsByRoom/'+eq;

    function doAjax(){$.ajax({
        url: urld,
        type: 'GET',
        dataType: 'json',
        responseType: "JSON",
        success: function (response) {
            console.log(response);
            let equipmentsWithConsumptions = new Map();
            let equipmentNames;
            const highChartSeries = [];
            equipmentsWithConsumptions = response;
            equipmentNames = Object.keys(equipmentsWithConsumptions);

            equipmentNames.forEach(equipmentName => {
                highChartSeries.push({
                    name: equipmentName,
                    data: equipmentsWithConsumptions[equipmentName].map(p => p.consomption).reverse()
                });
            });

            const highChartSum = getHighChartSeriesSum(highChartSeries);
            highChartSeries.push({
                name: 'Somme',
                data: highChartSum
            });

            displayRoundedSomme(highChartSum);

            const min = new Date();
            min.setSeconds(min.getSeconds() - 9)

            var chart = Highcharts.chart('consomdevicescharts', {
                title: {
                    text: 'Consommation Energie Pièce (les 25 dernières minutes)'
                },

                subtitle: {
                    text: 'Smart BEPOS (calcul chaque 15 secondes)'
                },

                credits: {
                    enabled: false
                },

                yAxis: {
                    title: {
                        text: 'Consommation (Kw/h)'
                    }
                },

                xAxis: {
                    title: {
                        text: 'chaque 10 secondes'
                    },
                    visible : false
                    // min: min.getSeconds()
                    // categories:
                },

                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle'
                },

                plotOptions: {
                    series: {
                        label: {
                            connectorAllowed: false
                        },
                        pointStart: min.getSeconds()
                    }
                },

                series: highChartSeries,
                responsive: {
                    rules: [{
                        condition: {
                            maxWidth: 500
                        },
                        chartOptions: {
                            legend: {
                                layout: 'horizontal',
                                align: 'center',
                                verticalAlign: 'bottom'
                            }
                        }
                    }]
                }

            });
        }
    });}

    doAjax();
    setInterval(doAjax,15000);


});

function displayRoundedSomme(sommeArray) {
    document.getElementById('sommeLastConsRoom').innerHTML =
        sommeArray[sommeArray.length - 1].toFixed(2)+ " Kw/h";
}


function getHighChartSeriesSum(series) {
    let producteurProductionsSum = [];
    let i = 0;
    series[0].data.forEach(d => {
        producteurProductionsSum[i] = 0;
        i++;
    });

    i = 0;
    series.forEach(serie => {
        i = 0;
        serie.data.forEach(d => {
            producteurProductionsSum[i] += 1 * d;
            i++;
        })
    });
    return producteurProductionsSum;
}


