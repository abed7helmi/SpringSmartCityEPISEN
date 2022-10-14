import{connexionBack2} from "./habitation.js"
$(document).ready(function () {

    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const eq = urlParams.get('habitationid')

    var urld=connexionBack2+'consomptionsByHabitation/'+eq;
    console.log(urld);

    function doAjax(){$.ajax({
        url: urld,
        type: 'GET',
        dataType: 'json',
        responseType: "JSON",
        success: function (response) {
            //for highsharts
            const highChartSeries = Object.entries(response)
            //affichage conso total
            const values = Object.values(response)
            const reducer = (accumulator, curr) => accumulator + curr;
            document.getElementById('consototal').innerHTML =values.reduce(reducer).toFixed(2)+" Kw/h";
            //affichage conso par piéce
            highChartSeries.forEach(([key, value]) => document.getElementById(key).innerHTML =value.toFixed(2) +" Kw/h"); //




        var chart = Highcharts.chart('chartallrooms', {
    chart: {
        type: 'column'
    },
    title: {
        text: 'Consommation par pièces'
    },
    credits:{
        enabled:false
    },
    subtitle: {
        text: 'Smart BEPOS (calcul chaque 15 secondes)'
    },
    xAxis: {
        type: 'category',
        labels: {
            rotation: -45,
            style: {
                fontSize: '13px',
                fontFamily: 'Verdana, sans-serif'
            }
        }
    },
    yAxis: {
        min: 0,
        title: {
            text: 'Consommation KW/h'
        }
    },
    legend: {
        enabled: false
    },
    tooltip: {
        pointFormat: 'consommation instantanée: <b>{point.y:.1f} Kh/h</b>'
    },
    series: [{
        name: 'Consommation',
        data:highChartSeries,
        dataLabels: {
            enabled: true,
            rotation: -90,
            color: '#FFFFFF',
            align: 'right',
            format: '{point.y:.1f}', // one decimal
            y: 10, // 10 pixels down from the top
            style: {
                fontSize: '13px',
                fontFamily: 'Verdana, sans-serif'
            }
        }
    }]
});
        }
    });}

    doAjax();
    setInterval(doAjax,15000);
});