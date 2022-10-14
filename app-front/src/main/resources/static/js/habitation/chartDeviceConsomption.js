import{connexionBack2} from "./habitation.js"
$(document).ready(function () {

    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const eq = urlParams.get('idEq')
    var urld=connexionBack2+'consomption/'+eq;


    function doAjax(){$.ajax({

        url: urld,
        type: 'GET',
        dataType: 'json',
        responseType: "JSON",
        success: function (response) {

            const highChartSeries = [];
            const queryString = window.location.search;
            const urlParams = new URLSearchParams(queryString);
            const eq = urlParams.get('idEq')
            const equipment = response[0].equipment;
            const data = response.map(c => c.consomption).reverse();
            highChartSeries.push({
                name: equipment.name,
                data: data
            });


            document.getElementById('sommeLastCons').innerHTML =data[data.length -1].toFixed(2)+ " Kw/h";
            document.getElementById('devicename').innerHTML =equipment.name;
            document.getElementById('devicepower').innerHTML =equipment.power;
            document.getElementById('devicetype').innerHTML =equipment.type;

            var chart = Highcharts.chart('deivceconsomption', {

                title: {
                    text: 'consommation équipement : '+equipment.name +" (les 3 dernières minutes)"
                },

                credits: {
                    enabled: false
                },

                subtitle: {
                    text: 'Smart BEPOS (calcul chaque 15 secondes)'
                },

                yAxis: {
                    title: {
                        text: 'consommation en Kw/h'
                    }
                },

                xAxis: {
                    title: {
                        // rangeDescription: 'Range: 2010 to 2017'
                    },
                    visible : false
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
                        pointStart: 1
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

    });

    }

    doAjax();
    setInterval(doAjax ,15000);


});

















//
//
//
//
//
//

