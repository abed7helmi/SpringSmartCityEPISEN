$(function() {
    //Constants
    var ROOM = ".room";
    var ROOM_MENU = ".room-menu";

    //Functions
    roomMenuHandler(ROOM, ROOM_MENU);
    
});

function roomMenuHandler(room, roomMenu) {
    var urls = [];
    $("html body").on("click", room, function(e) {
        var type = $(this).data("type-room");
        if($(roomMenu).hasClass("hide")) {
            var id = $(this).data("id-room");

            $(roomMenu).find(".id-room").each(function() {
                var url = $(this).attr('href');
                urls.push(url);
                $(this).attr('href', url + "/" + id);
            });

            if(type == 'SR') {
                $(roomMenu).find(".type-room-sr").each(function() {
                    $(this).removeClass("hide");
                });
            }
            else if(type == 'BI') {
               $(roomMenu).find(".type-room-bi").each(function() {
                      $(this).removeClass("hide");
               });
            }
            else if(type == 'OS') {
                $(roomMenu).find(".type-room-os").each(function() {
                      $(this).removeClass("hide");
                });
            }

        } else { //quand y'a pas le hide et quand on click sur une room
            $(roomMenu).find(".id-room").each(function(index) {
                $(this).attr('href', urls[index]);
            });
            urls = [];

            $(roomMenu).find(".type-room-sr, .type-room-os, .type-room-bi").each(function() {
               $(this).addClass("hide");
            });

        }


        $(roomMenu).css("left", e.pageX);
        $(roomMenu).css("top", e.pageY);
        $(roomMenu).toggleClass("hide");
        /*$('html').on("click", function(e) {
           if(!$(e.target).hasClass(room)) {
               $('.menu').remove();
           }
        }); */
    });
}

function findPath(){

        var select1 = document.getElementById('roomsList1');
        var id_room_from = select1.options[select1.selectedIndex].value;
        var select2 = document.getElementById('roomsList2');
        var id_room_to = select2.options[select2.selectedIndex].value;

        $.ajax({
                url: 'http://172.31.249.161:8080/dwppath/'+id_room_from+'/'+id_room_to,
                headers: {
                    'Content-Type': 'application/json'
                },
                type: 'GET',
                dataType: 'json',
                success: function (data) {

                    drawPath(id_room_from,id_room_to,data);
                },
                error: function () {
                    alert("Erreur calcul du chemin.")
                }
            });     

}

function drawPath(id_room_from,id_room_to,path_data){
    $.ajax({
        url: 'http://172.31.249.161:8080/dwppath/coordinates/'+id_room_from+'/'+id_room_to,
        headers: {
            'Content-Type': 'application/json'
        },
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            document.getElementById('dynamic_path').remove();
            var svg = document.getElementById('path');
            var ns = 'http://www.w3.org/2000/svg';
            var path_d = "";
            var dist;
            var v;var h;

            if(data[0].archi == 1){
                    v=20;
                    h=34;
            }
            else if(data[0].archi == 2){
                v=30;
                h=45;
            }

            /**Initialize start point**/
            switch(data[0].direction){
                case "S":
                    dist = data[0].area_x +data[0].coordinate*32 + data[0].position;
                    path_d += "m " + dist + " " + data[0].area_y;
                    path_d += " v -"+v;
                    break;
                case "N":
                    dist = data[0].area_x +data[0].coordinate*32 + data[0].position;;
                    path_d += "m " + dist + " " + data[0].area_y;
                    path_d += " v "+v;
                    break;
                case "O":
                    dist = data[0].area_y +data[0].coordinate*32 + data[0].position;;
                    path_d += "m " + data[0].area_x + " " + dist;
                    path_d += " h "+h;
                    break;
                case "E":
                    dist = data[0].area_y +data[0].coordinate*32 + data[0].position;;
                    path_d += "m " + data[0].area_x + " " + dist;
                    path_d += " h -"+h;
                    break;
            }


            if(data[0].id_hallway == data[1].id_hallway){
                dist = data[1].coordinate*32 - data[0].coordinate*32 + data[1].position;
                switch(data[1].direction){
                    case "S":
                        path_d += " h " + dist;
                        path_d += " v "+v;
                        break;
                    case "N":
                        path_d += " h " + dist;
                        path_d += " v -"+v;
                        break;
                    case "O":
                        path_d += " v " + dist;
                        path_d += " h -"+h;
                        break;
                    case "E":
                        path_d += " v " + dist;
                        path_d += " h "+h;
                        break;
                }
            }
            else {
                for(var i = 1;i<path_data.length;i++){

                    path_d += " L " + path_data[i].inter_x +" "+ path_data[i].inter_y;

                    if( i == path_data.length-1 ){

                        if(data[1].direction == "N"){
                            var y = data[1].area_y + v;
                        }
                        else if(data[1].direction == "S"){
                            var y = data[1].area_y - v;
                        }
                        else if(data[1].direction == "E"){
                            var x = data[1].area_x - h;
                        }
                        else if(data[1].direction == "O"){
                            var x = data[1].area_x + h;
                        }
                        switch(path_data[i].direction){
                            case "N":
                                path_d += " v -"+v;
                                var dist = data[1].coordinate*32 + data[1].area_y + data[1].position; //hallway_width;
                                path_d += " L "+x+" "+dist;
                                break;
                            case "S":
                                path_d += " v "+v;
                                var dist = data[1].coordinate*32 + data[1].area_y + data[1].position; //hallway_width;
                                path_d += " L "+x+" "+dist;
                                break;
                            case "E":
                                path_d += " h "+h;
                                var dist = data[1].coordinate*32 + data[1].area_x + data[1].position; //hallway_width;
                                path_d += " L "+dist+" "+y;
                                break;
                            case "O":
                                path_d += " h -"+h;
                                var dist = data[1].coordinate*32 + data[1].area_x + data[1].position; //hallway_width;
                                path_d += " L "+dist+" "+y;
                                break;
                        }

                        switch(data[1].direction){
                            case "S":
                                path_d += " v "+v;
                                break;
                            case "N":
                                path_d += " v -"+v;
                                break;
                            case "O":
                                path_d += " h -"+h;
                                break;
                            case "E":
                                path_d += " h "+h;
                                break;
                        }
                    }
                }
            }

            console.log("path is :" + path_d);
            var path = document.createElementNS(ns,'path');
            path.setAttribute('fill', 'transparent');
            path.setAttribute('stroke','red');
            path.setAttribute('stroke-width','3');
            path.setAttribute('marker-end','url(#arrowhead)');
            path.setAttribute('d', path_d);
            path.setAttribute('id', 'dynamic_path');
            svg.appendChild(path);
        },
        error: function () {
            alert("Erreur traçage du chemin.")
        }
    });
}
