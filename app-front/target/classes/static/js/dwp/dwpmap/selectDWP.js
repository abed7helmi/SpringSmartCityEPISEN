$(function() {

    var buildings = document.getElementById("buildings");

    buildings.addEventListener("change", function(){

        var url_dwp
        var id_building = buildings.options[buildings.selectedIndex].value;
        if(action == "showMap" || action == "findPath"){
            url_dwp = 'http://172.31.249.161:8080/dwp/building/'+id_building+ '/true';
        }
        else{
            url_dwp = 'http://172.31.249.161:8080/dwp/building/'+id_building;
        }
        $.ajax({
            url: url_dwp,
            headers: {
                'Content-Type': 'application/json'
            },
            type: 'GET',
            dataType: 'json',
            success: function (data) {

                $("#dwp").empty();
                var dwp = document.getElementById("dwp");

                for(var i=0;i<data.length;i++){

                    var option = document.createElement("option");
                    option.value = data[i].id_dwp;

                    if(action == "configMap" && data[i].configured){
                        option.text = '(T)Étage ' + data[i].floor_number;
                        option.style.background = "#b7e7c6";
                    }
                    else if (action == "configMap" && data[i].configured == false){
                        option.text = '(F)Étage ' + data[i].floor_number;
                        option.style.background = "#ff3261";
                    }
                    else {
                        option.text = 'Étage ' + data[i].floor_number;
                    }

                    dwp.appendChild(option);
                }

            },
            error: function () {
                alert("Erreur")
            }
        });
    });
    var event = new Event('change');

// Dispatch it.
    buildings.dispatchEvent(event);
});