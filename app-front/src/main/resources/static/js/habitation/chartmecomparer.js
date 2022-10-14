import{connexionBack2} from "./habitation.js"
$(document).ready(function () {

    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const eq = urlParams.get('habitationid')

    var urld=connexionBack2+'comparisonhabitation/'+eq;
    function doAjax(){$.ajax({
        url: urld,
        type: 'GET',
        dataType: 'json',
        responseType: "JSON",
        success: function (response) {
            //for highsharts

            var values = Object.values(response);
            var keys = Object.keys(response).reverse();


            var conso=[];
            var prod=[];
            values.forEach(element =>{
                conso.push(element.conso/5760);
                prod.push(element.prod/5760) ;}

            );


            var chart =Highcharts.chart('chartmecomparer', {
                chart: {
                    type: 'column'
                },
                credits:{
                    enabled:false
                },
                title: {
                    text: 'Me comparer par jour'
                },
                subtitle: {
                    text: 'Smart BEPOS (calcul chaque 15 secondes)'
                },
                xAxis: {
                    categories: keys,
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'en KW/h'
                    }
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                        '<td style="padding:0"><b>{point.y:.1f} KW/h</b></td></tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                },
                series: [{
                    name: 'Consommation de mon logement',
                    data: conso.reverse()

                }, {
                    name: 'Production BEPOS',
                    data: prod.reverse()
                }]
            });
        }
    });}

    doAjax();
    // setInterval(doAjax,15000);
});



