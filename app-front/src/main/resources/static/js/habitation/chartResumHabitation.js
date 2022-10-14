import{connexionBack2} from "./habitation.js"
$(document).ready(function () {

    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const eq = urlParams.get('idhabitation')

    var urld=connexionBack2+'resumhabitation/'+eq;
    function doAjax(){$.ajax({
        url: urld,
        type: 'GET',
        dataType: 'json',
        responseType: "JSON",
        success: function (response) {
            //for highsharts
            var highChartSeries = Object.entries(response)
            var values = Object.values(response)

            console.log(response);

            if (values[5]<0){
                document.getElementById('Balance').innerHTML =(values[1]*values[0]*-1).toFixed(2) +" Kw/h";
                // document.getElementById('Balance').classList.add("mystyle2");
                document.getElementById('Balance').style.fontSize="25px";
                document.getElementById('Balance').style.color="red"
                document.getElementById('Etat').innerHTML ="Excès de consommation";
                // document.getElementById('Etat').classList.add("mystyle2");
                document.getElementById('Etat').style.fontSize="25px";
                document.getElementById('Etat').style.color="red"
            }else{
                document.getElementById('Balance').innerHTML =(values[1]*values[0]*-1).toFixed(2) +" Kw/h";
                // document.getElementById('Balance').classList.add("mystyle");
                document.getElementById('Balance').style.fontSize="25px";
                document.getElementById('Balance').style.color="green"
                document.getElementById('Etat').innerHTML ="Consommation modéré";
                // document.getElementById('Etat').classList.add("mystyle");
                document.getElementById('Etat').style.fontSize="25px";
                document.getElementById('Etat').style.color="green"
            }
            var chart = Highcharts.chart('habitationcharts', {
                chart: {
                    height:550,
                    type: 'variablepie'
                },

                credits:{
                    enabled:false
                },
                title: {
                    text: "Analyse énérgétique de l'habitation"
                },
                subtitle: {
                    text: 'Smart BEPOS (calcul chaque 15 secondes)'
                },
                tooltip: {
                    headerFormat: '',
                    pointFormat: '<span style="color:{point.color}">\u25CF</span> <b> {point.name}</b><br/>' +
                        'Energie (Kw/h): <b>{point.y}</b><br/>'

                },
                series: [{
                    minPointSize: 10,
                    innerSize: '20%',
                    zMin: 0,
                    name: 'countries',
                    data: [{
                        name: 'Consommation Habitation',
                        y: values[3],
                        z: values[3]
                    }, {
                        name: 'Production BEPOS',
                        y: values[4],
                        z: values[4]
                    }]
                }]
            });

            var chart = Highcharts.chart('habitationcharts2', {
                chart: {
                    height:550,
                    type: 'variablepie'
                },

                credits:{
                    enabled:false
                },
                title: {
                    text: "Analyse énérgétique de l'habitation par m²"
                },
                subtitle: {
                    text: 'Smart BEPOS (calcul chaque 15 secondes)'
                },
                tooltip: {
                    headerFormat: '',
                    pointFormat: '<span style="color:{point.color}">\u25CF</span> <b> {point.name}</b><br/>' +
                        'Energie (Kw/h): <b>{point.y}</b><br/>'
                    // +
                    // 'Population density (people per square km): <b>{point.z}</b><br/>'
                },
                series: [{
                    minPointSize: 10,
                    innerSize: '80%',
                    zMin: 0,
                    name: 'countries',
                    data: [{
                        name: 'Production BEPOS par m2',
                        y: values[6],
                        z: values[6] * 400
                    }, {
                        name: 'Consommation par m2',
                        y: values[2],
                        z: values[2] * 400
                    }]
                }]
            });

        }
    });}
    doAjax();
    setInterval(doAjax,15000);
});

